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

    private static final Logger logger = LoggerFactory.getLogger(ShellUtils.class);

    public static int shellExecute(String ip, Integer port, String username, String password, String command, List<String> stdout) {
    	//String S_PATH_FILE_PRIVATE_KEY = "C:/Users/hdwang/.ssh/known_hosts";
    	int returnCode = 0;
        JSch jsch = new JSch();
       
        Session session = null;
        try {
            //创建session并且打开连接，因为创建session之后要主动打开连接
            if (port == 0) {
                session = jsch.getSession(username, ip, port);
            } else {
                session = jsch.getSession(username, ip, port);
            }

            session.setPassword(password);

            //关闭主机密钥检查，否则会导致连接失败，重要！！！
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            logger.info("连接服务器" + session.getHost());

            session.connect();
            //打开通道，设置通道类型，和执行的命令
            Channel channel = session.openChannel("exec");
            ChannelExec channelExec = (ChannelExec) channel;
            channelExec.setCommand(command);

            channelExec.setInputStream(null);
            BufferedReader input = new BufferedReader(new InputStreamReader((channelExec.getInputStream())));
            channelExec.connect(300000);
            logger.info("The remote command is:" + command);
            //接受远程服务器执行命令的结果

            String line = null;
            logger.info("stdout信息开始打印");
            while ((line = input.readLine()) != null) {
                stdout.add(line);
//              logger.info(line);
			}
			logger.info("stdout信息打印结束");
			input.close();

			// 得到returnCode
			if (channelExec.isClosed()) {
				returnCode = channelExec.getExitStatus();
			}

			// 关闭通道
			channelExec.disconnect();
			// 关闭session
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnCode;
	}

	public static int TestShell(String ip, Integer port, String username, String password, 
			Map<String,Object> map, List<String> stdout) {
		// String S_PATH_FILE_PRIVATE_KEY = "C:/Users/hdwang/.ssh/known_hosts";
		int returnCode = 0;
		JSch jsch = new JSch();
		String eXPERIMENTSNAME=(String) map.get("EXPERIMENTSNAME");
		String eNTERFILE=(String) map.get("DOWNLOADLINK");
		String pARAMSFILE=(String) map.get("PARAMSFILE");
		String dOWNLOADLINK=(String) map.get("DOWNLOADLINK");
		String fILENAME=(String) map.get("FILENAME");
		Session session = null;
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
			
			 //使用PrintWriter流的目的就是为了使用println这个方法
	        //好处就是不需要每次手动给字符串加\n
			PrintWriter printWriter = new PrintWriter(outputStream);
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
				if(start==1) {System.out.println(msg);
					if(msg.contains(eNTERFILE)){
						return 1;
						
					}
					else  return 0;
				}
				else if(msg.startsWith("root@3dformodel:")) start=1;
			}
		

			in.close();

			// 关闭session
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
		
	}
}