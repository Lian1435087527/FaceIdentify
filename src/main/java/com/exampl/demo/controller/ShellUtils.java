package com.exampl.demo.controller;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
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

            //得到returnCode
            if (channelExec.isClosed()) {
                returnCode = channelExec.getExitStatus();
            }

            //关闭通道
            channelExec.disconnect();
            //关闭session
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnCode;
    }
}