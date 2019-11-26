package com.exampl.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exampl.demo.baiduapi.HttpUtil;
import com.exampl.demo.faceidentify.BaseFunctionsBaidu;
import com.exampl.demo.faceidentify.Normalimage;
import com.exampl.demo.faceidentify_i.BaseFunctions_I;
import com.exampl.demo.faceidentify_i.Normalimage_I;
import com.exampl.demo.token.UserLoginToken;

@Controller
public class score {
	
	private String url_p="http://461af0d4-8da9-43ee-8285-e8d2619d98aa.southeastasia.azurecontainer.io/score";
	@UserLoginToken
	@RequestMapping("/score")
	@ResponseBody
	public ModelMap getul(@RequestParam("url") String url_g) throws Exception {
		
		

		String param = "{\"url\":\""+url_g+"\"}";
		//System.out.println(param);
		String accessToken=null;
		String result = HttpUtil.post(url_p,accessToken, "application/json", param);
		
		ModelMap Return=new ModelMap();
			
		 Return.put("result", result);
		
		
		
		
		return Return;
		

		}
	
	



}