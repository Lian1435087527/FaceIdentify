package com.exampl.demo.dbcontrol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exampl.demo.model.User;
@Controller
@RequestMapping("/login")
public class logincontroller {
    @Autowired
    private com.exampl.demo.Repositories.UserRepository UserRepository;

    @PostMapping
    
    public @ResponseBody ModelMap identifyUser(@RequestBody User user) {
    	ModelMap Return=new ModelMap();
    	String pw1=UserRepository.identify(user.getuser_id());
    	System.out.println(pw1);
    	String pw2=user.getpassword();
    	System.out.println(pw2);
    	if(pw1.equals(pw2)) {
    		Return.put("state", 1);
    	}
    	else {Return.put("state", 0);}
		return Return;
    }

    

    
    
}
