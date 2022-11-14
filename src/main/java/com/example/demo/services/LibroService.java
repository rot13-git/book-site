package com.example.demo.services;

import com.example.demo.entities.LibroEntity;
import com.example.demo.repositories.LibroEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroEntityRepository libroEntityRepository;

    public LibroEntity addBook(long isbn,String nome,String autore,String categoria,String descrizione,String imageName){
        if(libroEntityRepository.existsById(isbn)) throw new RuntimeException("Libro già esistente");
        LibroEntity ret = new LibroEntity(isbn,nome,autore,categoria,descrizione,imageName);
        libroEntityRepository.save(ret);
        return ret;
    }

    public LibroEntity bookById(long isbn){
        Optional<LibroEntity> ret = libroEntityRepository.findById(isbn);
        if (!ret.isPresent()) throw new RuntimeException("Libro non esistente");
        return ret.get();
    }


    public List<LibroEntity> getByName(String nome) {
        return libroEntityRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public List<LibroEntity> getAllBook() {
        return libroEntityRepository.findAll();
    }
}
