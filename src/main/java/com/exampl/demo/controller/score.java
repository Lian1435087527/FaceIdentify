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

	private String url_p1 = "http://a5b2350c-5154-49e4-82bd-958b9ce1703c.southeastasia.azurecontainer.io/score";
	private String url_p2 ="http://104.45.95.31:80/api/v1/service/facelivenessirsl/score";
	@UserLoginToken
	@RequestMapping("/score")
	@ResponseBody
	public ModelMap getul(@RequestParam("url") String url_g,@RequestParam("modelname") String modelname) throws Exception {

		String param = "{\"url\":\"" + url_g + "\"}";
		// System.out.println(param);
          String url_p=null;
          String authorization=null;
		switch (modelname){
			case "faceliveness":
				 url_p = url_p1;
				 authorization = null;
				 break;
			case "facelivenessirsl":
				 url_p=url_p2;
				authorization ="Bearer ps2vNRlAnRf2SKlUvd25p95AMXshNvrC";
		         break;}
		String result = HttpUtil.post(url_p, authorization, "application/json", param);
		//处理返回字符串
		String resultDic=result.substring(2,result.length()-2);
		String[] Diclist=resultDic.split("\", \"");
		ModelMap Return = new ModelMap();
		
		for(String i:Diclist) {
			String[] row=i.split("\": \"");
			Return.put(row[0], row[1]);
		}
		return Return;

	}
	/*
	public static void main(String[] arg) {
		score A=new score();
		ModelMap Return =null;
		try {
			Return =A.getul("https://cs1f9abf47a9b73x49c3x9c1.blob.core.windows.net/modelblob1/111/timg.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(Return);
	}
*/
}