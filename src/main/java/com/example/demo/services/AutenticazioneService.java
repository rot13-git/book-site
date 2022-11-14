package com.example.demo.services;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticazioneService implements UserDetailsService {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByUsername(username);
        if(user==null) throw new UsernameNotFoundException(username);
        UserDetails userDetails = null;
        userDetails = User.withUsername(username).password(user.getPassword()).authorities("UserLogged").build();
        return userDetails;
    }
    public UserEntity userByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userEntityRepository.findByUsername(username);
        if (user==null) throw new UsernameNotFoundException(username);
        return user;
    }
}
