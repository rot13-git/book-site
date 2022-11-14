package com.example.demo.repositories;

import com.example.demo.entities.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroEntityRepository extends JpaRepository<LibroEntity, Long> {
    LibroEntity findByNome (String nome);
    LibroEntity findByNomeContainingIgnoreCase(String nome);
    List<LibroEntity> findAllByNomeContainingIgnoreCase(String nome);
}