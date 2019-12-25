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
import com.exampl.demo.model.Tree;

import java.util.List;

@Controller
@RequestMapping("/gettree")
public class Treecontroller {
    @Autowired
    private com.exampl.demo.Repositories.TreeRepository TreeRepository;

    @PostMapping

    public @ResponseBody ModelMap gettree() {

        ModelMap Return=new ModelMap();
        List gtree=TreeRepository.gett();
        if(gtree==null){
            Return.put("message",0);

        }else {



                Return.put("tree",gtree);


            }

        return Return;
    }


}






