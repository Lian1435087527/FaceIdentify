package com.exampl.demo.dbcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.exampl.demo.model.User;
import com.exampl.demo.token.UserLoginToken;




@Controller
@RequestMapping("/register")
public class registerController {
    @Autowired
    private com.exampl.demo.Repositories.UserRepository UserRepository;

    @PostMapping
    
    public @ResponseBody ModelMap createUser(@RequestBody User user) {
    	ModelMap Return=new ModelMap();
    	if(UserRepository.save(user)==0) {
    		Return.put("state", 0);
    	}
    	else {
    		Return.put("state", 1);}
    	return Return;
        
    

   
    
    
}}