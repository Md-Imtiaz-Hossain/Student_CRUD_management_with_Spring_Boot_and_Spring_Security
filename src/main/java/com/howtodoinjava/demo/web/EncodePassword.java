package com.howtodoinjava.demo.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodePassword {

    public static void main(String[] args) {

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

        System.out.println(bc.encode("ABC"));
    }
}
