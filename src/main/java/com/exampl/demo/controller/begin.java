package com.exampl.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class begin {

	@RequestMapping(value="/")
	public String hello() {
		return "index";
		
	}

}
