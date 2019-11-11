package com.exampl.demo.dbcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.exampl.demo.model.User;




@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private com.exampl.demo.Repositories.UserRepository UserRepository;

    @PostMapping
    
    public @ResponseBody String createUser(@RequestBody User user) {
    	UserRepository.save(user);
        return String.format("Added %s", user);
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllPets() {
        return UserRepository.findAll();
    }

    
    
}