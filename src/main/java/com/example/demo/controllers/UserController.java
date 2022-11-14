package com.example.demo.controllers;


import com.example.demo.entities.UserEntity;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserEntity userEntity){
        try {
            UserEntity user = userService.registerUser(userEntity);
            return new ResponseEntity(user, HttpStatus.OK);
        }catch (RuntimeException re){
            return new ResponseEntity(re.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name")
    public ResponseEntity userByUsername(@RequestParam(value = "username") String username){
        try {
            UserEntity user = userService.getByUsername(username);
            return new ResponseEntity(user,HttpStatus.OK);
        }catch (RuntimeException re){
            return new ResponseEntity(re.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userService.getByUsername(name);
        userService.deleteUser(userEntity);
        return new ResponseEntity("Eliminato con successo",HttpStatus.OK);
    }

    @GetMapping()
    public List<UserEntity> getAll(){
        return userService.getAll();
    }
}
