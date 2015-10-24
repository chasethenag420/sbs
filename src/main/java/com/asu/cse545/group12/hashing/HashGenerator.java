package com.asu.cse545.group12.hashing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {

    public String getHashedPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
    
//    public static void main(String[] args)
//    {
//    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    	System.out.println(passwordEncoder.encode("Adminus1"));
//    	System.out.println(passwordEncoder.matches("Adminus1", "$2a$10$PTsKNwRINd4mTA/JYuzPcOM3mX8HGdt4rS/h4S3oeu862fIcTSv6W"));
//    }
}