package com.exampl.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exampl.demo.faceidentify.BaseFunctionsAzure;
import com.exampl.demo.faceidentify.BaseFunctionsBaidu;
import com.exampl.demo.faceidentify.Normalimage;
import com.exampl.demo.faceidentify_i.BaseFunctions_I;
import com.exampl.demo.faceidentify_i.Normalimage_I;
import com.exampl.demo.token.UserLoginToken;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

@Controller
public class send {
	private String picret;
	@UserLoginToken
	@RequestMapping("/picup")
	@ResponseBody
	public ModelMap getimg(@RequestParam("pic") String pic) {
		pic=pic.substring(pic.indexOf(",")+1);
		ModelMap Return=new ModelMap();

		BaseFunctions_I temp=new BaseFunctionsBaidu();
		//System.out.println(temp.ISExistFace(pic));
		
		int state=temp.ISExistFace(pic);
		if(state==1) {
			
			Normalimage_I A=new Normalimage();
			Return.put("state", 1);
			
			picret=(A.SignFace(pic));
			
			Return.put("picret", picret);
			}
		else {Return.put("state", state);}		
		
		return Return;

		}
	
	



}
