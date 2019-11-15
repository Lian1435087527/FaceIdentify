package com.exampl.demo.controller;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ShellUtils {
	// 测试时用户名统一为admin
	private static String Username = "admin";
	private static final Logger logger = LoggerFactory.getLogger(ShellUtils.class);


	public static int TestShell(String ip, Integer port, String username, String password, Map<String, Object> map,
			int method) {
		int retstate = 0;
		JSch jsch = new JSch();
		Session session = null;
		// 非必填参数配置
		String Enterfile = (String) map.get("ENTERFILE");
		String Paramsfile = (String) map.get("PARAMSFILE");
		if (Enterfile.equals(""))
			map.replace("ENTERFILE", "train.py");
		if (Paramsfile.equals(""))
			map.replace("PARAMSFILE", "params.json");
		try {
			// 创建session并且打开连接，因为创建session之后要主动打开连接

			session = jsch.getSession(username, ip, port);

			session.setPassword(password);

			// 关闭主机密钥检查，否则会导致连接失败，重要！！！
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			logger.info("连接服务器" + session.getHost());
			// 开启session
			session.connect();
			// 连接channelshell
			ChannelShell channel = (ChannelShell) session.openChannel("shell");
			//channel.setPty(true);
			channel.setPty(true);
			
			channel.connect();
			InputStream inputStream = channel.getInputStream();//从远程端到达的所有数据都能从这个流中读取到
			OutputStream outputStream = channel.getOutputStream();//写入该流的所有数据都将发送到远程端
			
			 			// 执行命令
			if (method == 0)
				retstate = upload(map, inputStream, outputStream);
			else
				retstate = create(map, inputStream, outputStream);
<<<<<<< Upstream, based on origin/master

<<<<<<< Upstream, based on origin/master
			String cmd = "sudo -i";
	        printWriter.println(cmd);
	        
	        String cmd2 ="cd /home/MLmodel/test";

	        printWriter.println(cmd2);

	        String cmd3 ="wget "+dOWNLOADLINK;
	        printWriter.println(cmd3);
	        String cmd4;
	        
	        if(fILENAME.substring(fILENAME.length()-3).equals("rar")) {
	        	 cmd4 ="unrar x "+fILENAME;
	        }else {
	        	 cmd4 ="unzip "+fILENAME;
	        }
	        
	        printWriter.println(cmd4);
	        String cmd5 ="ls -f";
	        printWriter.println(cmd5);
	        
	        printWriter.println("exit");//加上个就是为了，结束本次交互

	        printWriter.flush();
	
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

			String msg = null;
			int start=0;

			while ((msg = in.readLine()) != null) {
<<<<<<< Upstream, based on origin/master
<<<<<<< Upstream, based on origin/master
				
				if(msg.startsWith("root@3dformodel:/home/MLmodel/test# ls -f")) start=1;
				if(start==1)System.out.println(msg);
				if(msg.contains(eNTERFILE)){
					return 1;
=======
<<<<<<< Upstream, based on origin/master
=======

>>>>>>> 53ac31f 11-11 整合结束
				if(start==1) {System.out.println(msg);
					if(msg.contains(eNTERFILE)){
						return 1;
						
					}
					else  return 0;
>>>>>>> 20671fc 2019-11-11 02整合
				}
<<<<<<< Upstream, based on origin/master
				else  return 0;}
=======
				else if(msg.startsWith("root@3dformodel:")) start=1;

			}
>>>>>>> 20671fc 2019-11-11 02整合
		

			in.close();

=======
			
>>>>>>> 54bd26c 11-12 整合前
=======
		
>>>>>>> 5a3a20b 11-15 整合前
			// 关闭session
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retstate;

	}

	/**
	 * create 按钮指令
	 * 
	 * @param map          json包信息
	 * @param inputStream
	 * @param outputStream
	 * @return
	 * @throws IOException
	 */
	private static int create(Map<String, Object> map, InputStream inputStream, OutputStream outputStream)
			throws IOException {
		// TODO Auto-generated method stub
		int retstate = -1;
		// 使用PrintWriter流的目的就是为了使用println这个方法
		// 好处就是不需要每次手动给字符串加\n
		PrintWriter printWriter = new PrintWriter(outputStream);
		String Experimentsname = (String) map.get("EXPERIMENTSNAME");
		String Enterfile = (String) map.get("ENTERFILE");
		String Paramsfile = (String) map.get("PARAMSFILE");
		String Usedframe = (String) map.get("USEDFRAME");
		String Pip_packages = (String) map.get("PIP_PACKAGES");
		String Computertarger = (String) map.get("COMPUTERTARGER");
		// linux cmd命令
		String getroot = "sudo -i";
		printWriter.println(getroot);

		String enterfolder = "cd /home/MLmodel/";
		printWriter.println(enterfolder);

		String runupload = "python upload.py" + " --Username " + Username + " --Experimentsname " + Experimentsname
				+ " --Enterfile " + Enterfile + " --Paramsfile " + Paramsfile + " --Usedframe " + Usedframe
				+ " --Computertarger " + Computertarger + " --ISblob True";
		if (!Pip_packages.equals(""))
			runupload += (" --Pip_packages " + Pip_packages);

		printWriter.println(runupload);

		printWriter.println("exit");
		printWriter.println("exit");// 加上个就是为了，结束本次交互

		// 执行命令
		printWriter.flush();
		// 返回值判定操作
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String msg = null;
		int start = 0;

		while ((msg = in.readLine()) != null) {

			if (start == 1) {
				System.out.println(msg);
				if (msg.contains("create complete")) {
					retstate = 1;
					break;
				}
			} else if (msg.contains("python upload.py"))
				// 从上面那条命令开始 判定
				start = 1;
		}
		in.close();
		return retstate;
	}

	/**
	 * upload 按钮命令
	 * 
	 * @param map          json包信息
	 * @param inputStream
	 * @param outputStream
	 * @return
	 * @throws IOException
	 */
	private static int upload(Map<String, Object> map, InputStream inputStream, OutputStream outputStream)
			throws IOException {
		int retstate = -1;
		PrintWriter printWriter = new PrintWriter(outputStream);
		String Experimentsname = (String) map.get("EXPERIMENTSNAME");
		String dOWNLOADLINK = (String) map.get("DOWNLOADLINK");
		String fILENAME = (String) map.get("FILENAME");

		// linux cmd命令
		String getroot = "sudo -i";
		printWriter.println(getroot);

		String createfolder = "mkdir /home/MLmodel/" + Username + "/" + Experimentsname;
		printWriter.println(createfolder);

		String enterfolder = "cd /home/MLmodel/" + Username + "/" + Experimentsname;
		printWriter.println(enterfolder);

		String download = "wget " + dOWNLOADLINK;
		printWriter.println(download);

		String unzip;
		if (fILENAME.substring(fILENAME.length() - 3).equals("rar")) {
			unzip = "unrar x " + fILENAME;
		} else {
			unzip = "unzip " + fILENAME;
		}
		printWriter.println(unzip);

		String rmzip = "rm " + fILENAME;
		printWriter.println(rmzip);

		String isexist = "ls -al|grep ^-";
		printWriter.println(isexist);

		// printWriter.println("exit");
		// printWriter.println("exit");// 加上个就是为了，结束本次交互

		// 执行命令
		printWriter.flush();
		// 返回值判定操作
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

		String msg = null;
		int start = 0;
		// 检查入口文件名是否正确
		boolean enter = false, params = false;
		String eNTERFILE = (String) map.get("ENTERFILE");
		String Paramsfile = (String) map.get("PARAMSFILE");
		while ((msg = in.readLine()) != null) {

			if (start == 1) {
				// XXX 需要结果取消下面代码的注释
				//System.out.println(msg);
				// 如果检测到文件名 进行标记
				if (msg.contains(eNTERFILE))
					enter = true;
				if (msg.contains(Paramsfile))
					params = true;
				if (msg.startsWith("root@3dformodel:"))
					break;
			} else if (msg.contains("ls -al|grep ^-"))
				// 从上面那条命令开始 判定
				start = 1;
		}
		if (enter == true && params == true)
			retstate = 1;
		else {
			
			if (enter == false && params == false)
				retstate = 0;
			else if (enter == true)
				retstate = -1;
			else
				retstate = -2;
			// 上传失败 删除文件夹
			printWriter.println("cd ..");
			printWriter.println("rm -r " + Experimentsname);

		}
		//结束输入流程序
		printWriter.println("exit");
		printWriter.println("exit");
		printWriter.flush();
		//清理输入流缓存
		while (in.readLine() != null) {}
		in.close();
		return retstate;
	}

	
}