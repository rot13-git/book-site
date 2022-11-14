package com.example.demo.controllers;


import com.example.demo.entities.ContenutoEntity;
import com.example.demo.entities.LibroEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.services.ContenutoService;
import com.example.demo.services.LibroService;
import com.example.demo.services.UserService;
import com.example.demo.support.ResponseMessage;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contenuto")
public class ContenutoController {

    @Autowired
    private ContenutoService contenutoService;
    @Autowired
    private UserService userService;
    @Autowired
    private LibroService libroService;


    @PostMapping("/create")
    public ResponseEntity createContenuto(@RequestBody  Map<String, String> json){
        try{
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity userEntity = userService.getByUsername(name);
            LibroEntity libroEntity = libroService.bookById(Long.parseLong(json.get("libro_isbn")));
            ContenutoEntity contenutoEntity = contenutoService.createContenuto(json.get("tag"),userEntity,libroEntity,json.get("contenuto"),json.get("titolo"));
            return new ResponseEntity(contenutoEntity, HttpStatus.OK);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity(runtimeException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public List<ContenutoEntity> contenutoSearch(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "tag") Optional<String> tag,
                                                 @RequestParam(value ="libro") Optional<String> libro){
        return contenutoService.search(tag.orElse(""),libro.orElse(""),pageNumber,pageSize);
    }

    @GetMapping("/search/{username}")
    public List<ContenutoEntity> contenutoUser(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                               @PathVariable(value="username") String username){
        return contenutoService.contenutoByUser(username,pageNumber,pageSize);
    }
    @PostMapping("/delete")
    public ResponseEntity deleteContenuto(@RequestBody @Valid ContenutoEntity contenutoEntity){
        contenutoService.deleteContenuto(contenutoEntity);
        return new ResponseEntity<>(new ResponseMessage("Eliminata con successo!"),HttpStatus.OK);
    }

    @PostMapping("/modify")
    public ResponseEntity modifyContenuto(@RequestBody @Valid ContenutoEntity contenutoEntity){
        ContenutoEntity modified = contenutoService.modifyContenuto(contenutoEntity);
        return new ResponseEntity(modified,HttpStatus.OK);
    }
}
