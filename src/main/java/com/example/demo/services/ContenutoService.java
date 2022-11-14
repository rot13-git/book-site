package com.example.demo.services;

import com.example.demo.entities.ContenutoEntity;
import com.example.demo.entities.LibroEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.ContenutoEntityRepository;
import com.example.demo.repositories.LibroEntityRepository;
import com.example.demo.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContenutoService {

    @Autowired
    private ContenutoEntityRepository contenutoEntityRepository;
    @Autowired
    private LibroEntityRepository libroEntityRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;

    public ContenutoEntity createContenuto(String tag, UserEntity userEntity, LibroEntity libroEntity, String contenuto,String titolo){
        ContenutoEntity contenutoEntity = new ContenutoEntity(tag,userEntity,libroEntity,contenuto,titolo);
        contenutoEntityRepository.save(contenutoEntity);
        return contenutoEntity;
    }

    public ContenutoEntity modifyContenuto(ContenutoEntity contenutoEntity){
        contenutoEntityRepository.save(contenutoEntity);
        return contenutoEntity;
    }

    public void deleteContenuto(ContenutoEntity contenutoEntity){
        contenutoEntityRepository.delete(contenutoEntity);
    }

    public List<ContenutoEntity> searchByTag(String tag,int pageNumber, int pageSize){
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<ContenutoEntity> page = contenutoEntityRepository.findByTag(paging,tag);
        if(page.hasContent()) {
            return page.getContent();
        }else {return  new ArrayList<>();}
    }

    public List<ContenutoEntity> search(String tag, String libro,int pageNumber, int pageSize){
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        if(tag!=""){
            if(libro!=""){
                LibroEntity libroEntity = libroEntityRepository.findByNomeContainingIgnoreCase(libro);
                Page<ContenutoEntity> page = contenutoEntityRepository.findByTagAndLibroEntity(paging,tag,libroEntity);
                if(page.hasContent()) {
                    return page.getContent();
                }else {return  new ArrayList<>();}
            }
            Page<ContenutoEntity> page = contenutoEntityRepository.findByTag(paging,tag);
            if(page.hasContent()) {
                return page.getContent();
            }else {return  new ArrayList<>();}
        }
        if(libro!=""){
            LibroEntity libroEntity = libroEntityRepository.findByNomeContainingIgnoreCase(libro);
            Page<ContenutoEntity> page = contenutoEntityRepository.findByLibroEntity(paging,libroEntity);
            if(page.hasContent()) {
                return page.getContent();
            }else {return  new ArrayList<>();}
        }
       Page<ContenutoEntity> page = contenutoEntityRepository.findAll(paging);
        if(page.hasContent()) {
            return page.getContent();
        }else {return  new ArrayList<>();}
    }

    public List<ContenutoEntity> contenutoByUser(String username, int pageNumber, int pageSize) {
        try{
            Pageable paging = PageRequest.of(pageNumber, pageSize);
            UserEntity user = userEntityRepository.findByUsername(username);
            Page<ContenutoEntity> page = contenutoEntityRepository.findByUserEntity(paging,user);
            if(page.hasContent()) {
                return page.getContent();
            }else {return  new ArrayList<>();}
        }catch (RuntimeException re){
            return new ArrayList<>();
        }
    }
}
