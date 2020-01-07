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
import org.json.JSONArray;

@Controller

public class pTreecontroller {
	@Autowired
	private com.exampl.demo.Repositories.TreeRepository TreeRepository;

	@PostMapping
	@RequestMapping("/posttree")
	public @ResponseBody ModelMap posttree(@RequestParam("tree") String ltree) {
		ModelMap Return = new ModelMap();


        Tree tree2 = new Tree();
        //System.out.println(ltree);
        JSONArray tree1=new JSONArray(ltree);

		for (int i = 0; i < tree1.length(); i++) {
			tree2.setTree_id(tree1.getJSONObject(i).getString("t_id"));
			tree2.setName(tree1.getJSONObject(i).getString("t_name"));
			tree2.setPid(tree1.getJSONObject(i).getString("t_pid"));
			// System.out.println(tree2);
			if (TreeRepository.savet(tree2) == 0) {
				Return.put("state", 0);
			} else {
				Return.put("state", 1);
			}
		}
		return Return;


	}



	@PostMapping
	@RequestMapping("/deldir")
	public @ResponseBody ModelMap deldir(@RequestParam("dirn") String trid) {

		ModelMap Return = new ModelMap();
		if (TreeRepository.deletet(trid) == 0) {
			Return.put("state", 0);
		} else {
			Return.put("state", 1);
		}
		return Return;

	}
	


}



