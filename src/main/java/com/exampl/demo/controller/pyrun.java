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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.exampl.demo.controller.ShellUtils;

@Controller
public class pyrun {
	private String IP = "207.46.136.243";
    private Integer PORT = 22;
    private String USERNAME = "azureuser";
    private String PASSWORD = "Ll1111111111";
    
	//@RequestMapping("/model")
	//@ResponseBody
	public ModelMap upload_1(@RequestBody Map<String,Object> map1) {
		
		ModelMap Return=new ModelMap();
	List<String> result = new ArrayList<>();
    /*int ret = ShellUtils.TestShell(IP, PORT, USERNAME, PASSWORD,map1,result);
   
    if (ret == 0) {
    	Return.put("state", 0);}
    
    else {Return.put("state", 1);}
    */
    return Return;
}}
	


	