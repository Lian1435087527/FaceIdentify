package com.exampl.demo.dbcontrol;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exampl.demo.model.User;
@Controller
@RequestMapping("/changepwd")
public class repassword {
    @Autowired
    private com.exampl.demo.Repositories.UserRepository UserRepository;

    @PostMapping

    public @ResponseBody ModelMap identifyUser(@RequestBody User user) {

        ModelMap Return=new ModelMap();
        User userForBase=UserRepository.findbyid(user.getuser_id());
        if(userForBase==null){
            Return.put("message",0);

        }else {
            if (!userForBase.getpassword().equals(user.getpassword())){
                Return.put("message",1);

            }else {
                String token = UserRepository.getToken(userForBase);
                Return.put("token", token);
                Return.put("userid", userForBase.getuser_id());
                Return.put("role",userForBase.getrole());
            }
        }
        return Return;
    }


}

