package com.exampl.demo.dbcontrol;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exampl.demo.model.User;
@Controller
@RequestMapping("/changepwd")
public class repassword {
    @Autowired
    private com.exampl.demo.Repositories.UserRepository UserRepository;

    @PostMapping

    public @ResponseBody ModelMap changepassword(@RequestParam String uid,@RequestParam String newpwd) {

        ModelMap Return=new ModelMap();
        User userForBase=UserRepository.findbyid(uid);
        if(userForBase==null){
            Return.put("message",0);

        }else {
            UserRepository.chanpwd(uid,newpwd);
        }
        return Return;
    }


}

