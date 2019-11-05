package com.exampl.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exampl.demo.faceidentify.BaseFunctionsAzure;
import com.exampl.demo.faceidentify.BaseFunctionsBaidu;
import com.exampl.demo.faceidentify.Normalimage;
import com.exampl.demo.faceidentify_i.BaseFunctions_I;
import com.exampl.demo.faceidentify_i.Normalimage_I;


@Controller
public class pyrun {
	
	@RequestMapping("/modelup")
	@ResponseBody
	public ModelMap getpy(@RequestParam("pymsg") String pymsg) {
		String backline = null;
		ModelMap Return=new ModelMap();
		
		Process proc;
        try {
        	File path = new File(ResourceUtils.getURL("classpath:").getPath());
        	if(!path.exists()) path = new File("");
        	String pypath=path.getPath();
        	
        	
            proc = Runtime.getRuntime().exec("python "+pypath+"\\mytee.py");// 执行py文件
            System.out.println(1);
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
            	 backline=line;
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
		
		
			
		Return.put("backline", backline);
			
		
		return Return;

		}}
