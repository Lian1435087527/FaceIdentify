package com.exampl.demo.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		//File f = new File("E:"+"face"+".jpg");
		//ImageIO.write(imgbuff, "jpg", f);
		pic=pic.substring(pic.indexOf(",")+1);
		BaseFunctions_I temp=new BaseFunctions();
		System.out.println(temp.ISExistFace(pic));
		Normalimage_I A=new Normalimage();
		picret=(A.SignFace(pic));
		//System.out.println(pic);
		//返回JSON对象（以ModelMap为载体）
		ModelMap ret=new ModelMap();
		ret.put("signface", picret);
		ret.put("fae", "1fdsa5f");
		return ret;
		}
	
	
}
