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
//    	System.out.println(passwordEncoder.matches("manager5", "$2a$10$.IssZ.C7opRE/dNg8ByLRuPHNPtBRhmJfxw1c9.WdpArd9VDeeRym"));
//    }
}