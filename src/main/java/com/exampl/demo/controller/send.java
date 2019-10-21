package com.exampl.demo.controller;


import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exampl.demo.faceidentify.BaseFunctions;
import com.exampl.demo.faceidentify.Normalimage;
import com.exampl.demo.faceidentify_i.BaseFunctions_I;
import com.exampl.demo.faceidentify_i.Normalimage_I;

@Controller
public class send {
	@RequestMapping("/picup")
	
	public String getimg(@RequestParam("pic") String pic) {
		//File f = new File("E:"+"face"+".jpg");
		//ImageIO.write(imgbuff, "jpg", f);
		pic=pic.substring(pic.indexOf(",")+1);
		BaseFunctions_I temp=new BaseFunctions();
		System.out.println(temp.ISExistFace(pic));
		//System.out.println(1111);
		Normalimage_I A=new Normalimage();
		String picret=(A.SignFace(pic));
		System.out.println(pic);
		return pic;}
	
}

