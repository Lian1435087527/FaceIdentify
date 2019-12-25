package com.exampl.demo.dbcontrol;
import com.exampl.demo.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exampl.demo.model.Tree;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/posttree")
public class pTreecontroller {
    @Autowired
    private com.exampl.demo.Repositories.TreeRepository TreeRepository;

    @PostMapping

    public @ResponseBody ModelMap posttree(@RequestBody String ltree) {

        ModelMap Return=new ModelMap();
        System.out.println(ltree);
        /*if(TreeRepository.savet(tree)==0) {
            Return.put("state", 0);
        }
        else {
            Return.put("state", 1);}
        return Return;*/
        Return.put("state", 1);
        return Return;


}}







