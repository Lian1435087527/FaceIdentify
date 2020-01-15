package com.exampl.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.exampl.demo.baiduapi.HttpUtil;
import com.exampl.demo.token.UserLoginToken;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.microsoft.azure.CloudException;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.rest.LogLevel;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MLendpointcontroller {
	public final File credFile = new File("azureauth.properties");
	private Azure azure;
	public MLendpointcontroller() {
		// TODO Auto-generated constructor stub
		File credFile = this.credFile;
		try {
			this.azure = Azure.configure().withLogLevel(LogLevel.BASIC).authenticate(credFile)
					.withDefaultSubscription();
		} catch (CloudException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@UserLoginToken
	@RequestMapping("/mlcon")
	@ResponseBody
	public ModelMap getul(@RequestParam("msg") int controltype)
			throws Exception {
		// System.out.println(param);
		int ret=-2;
		switch (controltype) {
		case 1:
			//url_p = url_p1;
			ret=openendpoint(azure);
			break;
		case 0:
			//url_p = url_p2;
			ret=closeVM(azure);
			break;
		}
		ModelMap Return = new ModelMap();
		Return.put("message", ret);
		return Return;
	
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	private int openendpoint(Azure azure) {
		// TODO Auto-generated method stub
		// 开启两个虚拟机
		VirtualMachine vmforpython = azure.virtualMachines().getByResourceGroup("3d智能云", "3dformodel"),
				vmforendpoint = azure.virtualMachines().getByResourceGroup("MC_trial_akscomputerf9f3885482_northeurope",
						"aks-agentpool-26973884-0");
		// 创建新线程来开启，避免等待冲突
		Thread startVM1 = new Thread(new ControlVM(vmforpython, ControlVM.START)),
				startVM2 = new Thread(new ControlVM(vmforendpoint, ControlVM.START));
		startVM1.start();
		startVM2.start();

		// 循环检测
		int deployresult = -2;
		try {
			// 两个都成功打开才跳出等待 15秒检测一次
			boolean VM1ready = false, VM2ready = false;
			while (!(VM1ready && VM2ready)) {
				// 更新状态
				vmforpython.refresh();
				vmforendpoint.refresh();
				Thread.currentThread().sleep(15000);
				// 因为vmforpython虚拟机的一个鬼畜bug，调用powerState时可能会返回null
				try {
					if (vmforendpoint.powerState().toString().split("/")[1].equals("running"))
						VM1ready = true;
					if (vmforpython.powerState().toString().split("/")[1].equals("running"))
						VM2ready = true;
				} catch (NullPointerException e) {
					// TODO: handle exception
					System.out.print("出现null问题");
				}
			}
			//停止打开虚拟机线程
			startVM1.stop();
			startVM2.stop();
			System.out
					.println("VM " + vmforpython.computerName() + " has state " + vmforpython.powerState().toString());
			System.out.println(
					"VM " + vmforendpoint.computerName() + " has state " + vmforendpoint.powerState().toString());
			// 等待30秒让虚拟机可以连接，若仍无法连接再等待30秒
			while (deployresult == -2) {
				Thread.currentThread().sleep(30000);
				deployresult = Deployendpoint();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			deployresult = -3;
		} catch (Exception e) {
			e.printStackTrace();
			deployresult = -4;
		}
		// resourceGroupVMs.start();
		// 根据结果关闭虚拟机
		if(deployresult==1) {//运行成功
			vmforpython.deallocate();
			System.out.println("部署成功 部署虚拟机已关闭");
		}
		else if(deployresult==0||deployresult==-1){
			//若为部署虚拟机错误 -1 0 则不关闭虚拟机
			System.out.println("部署失败 部署虚拟机和终端虚拟机开启等待下次部署");
		}else {//运行失败
			vmforpython.deallocate();
			vmforendpoint.deallocate();
			System.out.println("部署失败 部署虚拟机和终端虚拟机已关闭");
		}				
		// resourceGroupVMs.start();
		// System.exit(0);
		return deployresult;
	}

	private int Deployendpoint() {
		// TODO Auto-generated method stub
		JSch jsch = new JSch();
		Session session = null;
		int ret = -2;
		try {
			// 创建session并且打开连接，因为创建session之后要主动打开连接

			session = jsch.getSession("azureuser", "207.46.136.243", 22);

			session.setPassword("Ll1111111111");

			// 关闭主机密钥检查，否则会导致连接失败，重要！！！
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			// 开启session
			session.connect();
			// 连接channelshell
			ChannelShell channel = (ChannelShell) session.openChannel("shell");
			// channel.setPty(true);
			channel.setPty(true);

			channel.connect();
			InputStream inputStream = channel.getInputStream();// 从远程端到达的所有数据都能从这个流中读取到
			OutputStream outputStream = channel.getOutputStream();// 写入该流的所有数据都将发送到远程端

			// 执行命令
			// linux cmd命令
			PrintWriter printWriter = new PrintWriter(outputStream);
			String getroot = "sudo -i";
			printWriter.println(getroot);

			String enterfolder = "cd /home/deployEndpoint";
			printWriter.println(enterfolder);

			String rundeploy = "python deploy.py";

			printWriter.println(rundeploy);
			// 执行命令
			printWriter.flush();
			// 返回值判定操作
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

			String msg = null;

			boolean start = false;
			// 检查部署结果 因为有延迟，所以需要个while(true)循环
			while (true) {
				while ((msg = in.readLine()) != null) {
					if (start == false && msg.contains("# python deploy.py"))// msg.contains("ls -al|grep ^-")
						// 从上面那条命令开始 判定
						start = true;
					if (start == true) {
						// XXX 需要结果取消下面代码的注释
						System.out.println(msg);
						// 如果检测到成功或者失败标志 进行标记并跳出
						if (msg.contains("Succeeded")) {
							ret = 1;
							break;
						}

						if (msg.contains("Failed")) {
							ret = 0;
							break;
						}
						// 运行结束 但是返回非正常结果
						if (msg.startsWith("root@3dformodel:") && msg.endsWith("# "))
							ret = -1;
					}
				}
				// 停止位置 ret被改变
				if (ret != -2)
					break;
			}
			// 结束输入流程序
			printWriter.println("exit");
			printWriter.println("exit");
			printWriter.flush();
			// 清理输入流缓存
			while (in.readLine() != null) {
			}
			in.close();
			// 关闭session
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public int closeVM(Azure azure)  {
		VirtualMachine vmforpython = azure.virtualMachines().getByResourceGroup("3d智能云", "3dformodel"),
				vmforendpoint = azure.virtualMachines().getByResourceGroup("MC_trial_akscomputerf9f3885482_northeurope",
						"aks-agentpool-26973884-0");
		int ret=2;
		//关闭虚拟机
		try {
		vmforpython.deallocate();
		vmforendpoint.deallocate();
		}catch(Exception e) {
			ret=-5;
		}
		return ret;
	}

	public class ControlVM implements Runnable {
		private VirtualMachine VM;
		private int Operation;
		public static final int START = 0;
		public static final int STOP = 1;
		public static final int REFEASH = 2;

		// public static final int START=0;
		public ControlVM(VirtualMachine vm, int operation) {
			// TODO Auto-generated constructor stub
			this.VM = vm;
			this.Operation = operation;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (this) {
				switch (Operation) {
				case 0:
					System.out.println("虚拟机 " + VM.name() + "正在开启");
					VM.start();
					System.out.println("虚拟机 " + VM.name() + "开启完毕");
					break;
				case 1:
					System.out.println("虚拟机 " + VM.name() + "正在关闭");
//					VM.deallocate();
					VM.powerOff();
					System.out.println("虚拟机 " + VM.name() + "关闭完毕");
					break;
				case 2:
					System.out.println("虚拟机 " + VM.name() + "正在刷新");
					VM.refresh();
					System.out.println("虚拟机 " + VM.name() + "刷新完毕");
					break;
				default:
					System.out.println("虚拟机 操作输入错误");
					break;
				}
				this.notify();
			}

		}

		public VirtualMachine getVM() {
			return VM;
		}
	}

	/*
	 * public static void main(String[] arg) { try {
	 * 
	 * // ============================================================= //
	 * Authenticate MLendpointcontroller A = new MLendpointcontroller(); final File
	 * credFile = A.credFile;
	 * 
	 * Azure azure =
	 * Azure.configure().withLogLevel(LogLevel.BASIC).authenticate(credFile)
	 * .withDefaultSubscription();
	 * 
	 * // Print selected subscription System.out.println("Selected subscription: " +
	 * azure.subscriptionId()); System.out.println(A.openendpoint(azure));
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage());
	 * e.printStackTrace(); } }
	 */
}
