package com.howtodoinjava.demo.Spring_security;

import com.howtodoinjava.demo.model.StudentEntity;
import com.howtodoinjava.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private StudentRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        StudentEntity studentEntity = repository.findByEmail(email);

        if (studentEntity == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new CustomUserDetails(studentEntity);

    }
}
