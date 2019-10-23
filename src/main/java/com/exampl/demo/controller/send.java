package com.exampl.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.exampl.demo.faceidentify.BaseFunctions;
import com.exampl.demo.faceidentify.Normalimage;
import com.exampl.demo.faceidentify_i.BaseFunctions_I;
import com.exampl.demo.faceidentify_i.Normalimage_I;


@Controller
public class send {
	private String picret;
	@RequestMapping("/picup")
	@ResponseBody
	public ModelMap getimg(@RequestParam("pic") String pic) {
		pic=pic.substring(pic.indexOf(",")+1);
		ModelMap Return=new ModelMap();
		
		BaseFunctions_I temp=new BaseFunctions();
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
	private int times=0;
	/**
	 * 测试运行时间
	 */
	@SuppressWarnings("deprecation")
	private void Testtime() {
		Date date = new Date();
		times++;
		System.out.println(this.getClass().getName()+" "+new Integer(times).toString()+"->"+date.getMinutes()+":"+date.getSeconds());
	}
	
}
