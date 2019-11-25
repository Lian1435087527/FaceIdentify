package com.exampl.demo.controller;



import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class usemodel {
	
	@RequestMapping(value="/add")
	 public String test7(User user,Model model){
        System.out.println(user);
        System.out.println(21321421);
        model.addAttribute("message", user.getName() + " hello");
        return "/add.jsp";
    }
}
