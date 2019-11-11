package com.exampl.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exampl.demo.faceidentify.BaseFunctionsBaidu;
import com.exampl.demo.faceidentify.Normalimage;
import com.exampl.demo.faceidentify_i.BaseFunctions_I;
import com.exampl.demo.faceidentify_i.Normalimage_I;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import com.exampl.demo.controller.ShellUtils;
@Controller
public class moddefi {
	
	private static final Logger logger = LoggerFactory.getLogger(moddefi.class);

    private String IP = "207.46.136.243";
    private Integer PORT = 22;
    private String USERNAME = "azureuser";
    private String PASSWORD = "Ll1111111111";
    
		
		@RequestMapping("/identify")
		@ResponseBody
		public ModelMap upload(@RequestBody Map<String,Object> map) {
		
			ModelMap Return=new ModelMap();
		List<String> result = new ArrayList<>();
        int ret = ShellUtils.TestShell(IP, PORT, USERNAME, PASSWORD,map,result);
        System.out.println(ret);
        int state=0;
        if (ret == 0) {
        	Return.put("state", 0);}
        
        else {Return.put("state", 1);}

        return Return;
	}}
	

