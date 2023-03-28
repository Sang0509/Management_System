package com.example.manegement.controller;

import com.example.manegement.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

        @Autowired
        private LoginMapper loginMapper;

    @GetMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        Map<String, Object> res = new HashMap<>();
//        return loginMapper.login(username,password);
        String bool = loginMapper.login(username,password);
        res.put("bool", bool);
        return bool;
    }
}
