package com.example.demo.controllers;

import com.example.demo.entities.LibroEntity;
import com.example.demo.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping("/add")
    public ResponseEntity createBook(@RequestBody @Valid LibroEntity libroEntity){
        try {
            LibroEntity ret = libroService.addBook(libroEntity.getIsbn(),libroEntity.getNome(),
                                                   libroEntity.getAutore(),libroEntity.getCategoria(),libroEntity.getDescrizione(),libroEntity.getImageName());
            return new ResponseEntity(ret,HttpStatus.OK);
        }catch (RuntimeException re){
            return new ResponseEntity(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity getBookByID(@RequestParam(value = "isbn") long isbn){
        try{
            LibroEntity ret = libroService.bookById(isbn);
            return new ResponseEntity(ret,HttpStatus.OK);
        }catch (RuntimeException re){
            return new ResponseEntity(re.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/search")
    public List<LibroEntity> getBookByName(@RequestParam(value= "nome") String nome){
        return libroService.getByName(nome);
    }

    @GetMapping("/all")
    public List<LibroEntity> getAll(){
        return libroService.getAllBook();
    }
}
