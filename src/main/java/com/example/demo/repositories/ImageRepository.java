package com.example.demo.repositories;

import java.util.Optional;

import com.example.demo.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}