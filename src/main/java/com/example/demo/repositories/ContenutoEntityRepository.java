package com.example.demo.repositories;

import com.example.demo.entities.ContenutoEntity;
import com.example.demo.entities.LibroEntity;
import com.example.demo.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContenutoEntityRepository extends JpaRepository<ContenutoEntity, Long> {
    Page<ContenutoEntity> findByTag(Pageable page,String tag);
    Page<ContenutoEntity> findByTagAndLibroEntity(Pageable pageable, String tag, LibroEntity libroEntity);

    Page<ContenutoEntity> findByLibroEntity(Pageable paging, LibroEntity libroEntity);

    Page<ContenutoEntity> findByUserEntity(Pageable paging, UserEntity userEntity);
}