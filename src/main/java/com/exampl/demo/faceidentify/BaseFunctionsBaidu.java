package com.exampl.demo.faceidentify;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.util.ResourceUtils;

import com.baidu.aip.face.AipFace;

import com.exampl.demo.faceidentify_i.BaseFunctions_I;
import org.python.*;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.jcraft.jsch.Channel;


public class BaseFunctionsBaidu implements BaseFunctions_I {
	private static AipFace client;
	private static JSONObject Detect_Baidu;
	public static final String APP_ID = "17500318";
	public static final String API_KEY = "aT4Z4jLrrxNNz86yOWGtwaA0";
	public static final String SECRET_KEY = "MaQC5FIEzCQTUs2XfifYn2FMisybXPby";

	public BaseFunctionsBaidu() {
		// 初始化一个AipFace
		client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
	}

	@SuppressWarnings("finally")
	@Override
	public int ISExistFace(String imageB64) {
		// TODO Auto-generated method stub
		// 人脸检测
		int state=-2;
		try {
			Testtime();
			HashMap<String, String> map = new HashMap<>();
			map.put("max_face_num", new Integer(2).toString());
			Detect_Baidu=client.detect(imageB64, "BASE64", map);
			if (Detect_Baidu.getInt("error_code") == 0) {
				state = 1;
			} else {
				state = 0;
			}

		} catch (Exception e2) {
			state = -1;
		} finally {
			Testtime();
			//pyrun1();
			conn();
			return state;
		}
	}

	public static AipFace getClient() {

		return client;
	}

	private int times = 0;

	/**
	 * 测试运行时间
	 */
	@SuppressWarnings("deprecation")
	private void Testtime() {
		Date date = new Date();
		times++;
		System.out.println(this.getClass().getName() + " " + new Integer(times).toString() + "->" + date.getMinutes()
				+ ":" + date.getSeconds());
	}
	
	
	/*@SuppressWarnings("resource")
	private void pyrun1() {
		 PythonInterpreter interpreter = new PythonInterpreter();
		 String pypath=null;
		 try {
		File path = new File(ResourceUtils.getURL("classpath:").getPath());
     	if(!path.exists()) path = new File("");
     	pypath=path.getPath();
     	interpreter.execfile(pypath+"\\mytee.py");
		 }
		 catch (IOException e) {
	            e.printStackTrace();
	        } 
		
		
	}*/
	/**
	 * This program enables you to connect to sshd server and get the shell prompt.
	 * You will be asked username, hostname and passwd.
	 * If everything works fine, you will get the shell prompt. Output may
	 * be ugly because of lacks of terminal-emulation, but you can issue commands.
	 */
	   public void conn() {
		   final String USER="azureuser";
		    final String PASSWORD="Ll1111111111";
		    final String HOST="207.46.136.243";
		    final int DEFAULT_SSH_PORT=22;
	        try{
	            JSch jsch=new JSch();
	            Session session = jsch.getSession(USER,HOST,DEFAULT_SSH_PORT);
	            session.setPassword(PASSWORD);
	 
	            UserInfo userInfo = new UserInfo() {
	                @Override
	                public String getPassphrase() {
	                    System.out.println("getPassphrase");
	                    return null;
	                }
	                @Override
	                public String getPassword() {
	                    System.out.println("getPassword");
	                    return null;
	                }
	                @Override
	                public boolean promptPassword(String s) {
	                    System.out.println("promptPassword:"+s);
	                    return false;
	                }
	                @Override
	                public boolean promptPassphrase(String s) {
	                    System.out.println("promptPassphrase:"+s);
	                    return false;
	                }
	                @Override
	                public boolean promptYesNo(String s) {
	                    System.out.println("promptYesNo:"+s);
	                    return true;//notice here!
	                }
	                @Override
	                public void showMessage(String s) {
	                    System.out.println("showMessage:"+s);
	                }
	            };
	 
	            session.setUserInfo(userInfo);
	            session.setConfig("StrictHostKeyChecking", "no");
	            // It must not be recommended, but if you want to skip host-key check,
	            // invoke following,
	            // session.setConfig("StrictHostKeyChecking", "no");
	 
	            //session.connect();
	            session.connect(30000);   // making a connection with timeout.
	 
	            Channel channel=session.openChannel("shell");
	 
	            // Enable agent-forwarding.
	            //((ChannelShell)channel).setAgentForwarding(true);
	 
	            channel.setInputStream(System.in);
	      /*
	      // a hack for MS-DOS prompt on Windows.
	      channel.setInputStream(new FilterInputStream(System.in){
	          public int read(byte[] b, int off, int len)throws IOException{
	            return in.read(b, off, (len>1024?1024:len));
	          }
	        });
	       */
	 
	            channel.setOutputStream(System.out);
	 
	      /*
	      // Choose the pty-type "vt102".
	      ((ChannelShell)channel).setPtyType("vt102");
	      */
	 
	      /*
	      // Set environment variable "LANG" as "ja_JP.eucJP".
	      ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
	      */
	 
	            //channel.connect();
	            channel.connect(3*1000);
	        }
	        catch(Exception e){
	            System.out.println(e);
	        }}
	

	
	/**
	 * 获得Baidu人脸检测返回结果
	 * @return
	 */
	public static JSONObject getDetect_Baidu() {
		return Detect_Baidu;
	}
}
