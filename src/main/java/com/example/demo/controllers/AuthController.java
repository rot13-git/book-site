package com.example.demo.controllers;

import com.example.demo.support.AuthenticationBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basicauth")
public class AuthController {
    @GetMapping
    public AuthenticationBean auth(){
        return new AuthenticationBean("Authenticated");
    }

}
