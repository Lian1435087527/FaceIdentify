package com.exampl.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//@RestController
//@RequestMapping
public class send {
	@RequestMapping("/picup")
	public String getimg(@RequestParam("pic") String pic) {
		//File f = new File("E:"+"face"+".jpg");
		//ImageIO.write(imgbuff, "jpg", f);
		System.out.println(1111122222);
		System.out.println(pic);
		return pic;
		}
	
}