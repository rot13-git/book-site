package com.example.demo.services;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registerUser(UserEntity userEntity) throws RuntimeException{
        if(userEntityRepository.existsByUsername(userEntity.getUsername())){
            throw new RuntimeException("User esistente!");
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntityRepository.save(userEntity);
        return userEntity;
    }

    public List<UserEntity> getAll(){
        return userEntityRepository.findAll();
    }

    public UserEntity getByUsername(String username) throws RuntimeException{
        UserEntity ret = userEntityRepository.findByUsername(username);
        if(ret == null) throw new RuntimeException("User non esistente");
        return ret;
    }

    public boolean deleteUser(UserEntity userEntity){
        userEntityRepository.delete(userEntity);
        return true;
    }
}
