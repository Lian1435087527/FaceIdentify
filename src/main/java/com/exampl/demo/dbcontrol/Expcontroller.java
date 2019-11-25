package com.exampl.demo.dbcontrol;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.exampl.demo.model.Exp;
import com.exampl.demo.model.User;





public class Expcontroller {
    @Autowired
    private com.exampl.demo.Repositories.ExpRepository ExpRepository;

   
     
    public int expsave(Exp exp) {
		
    	ExpRepository.save(exp);
    
    
    
    
    
    
    
    	return 0;}
        
  }