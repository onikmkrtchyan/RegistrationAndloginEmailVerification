package com.onik.spring.security.jwt.utils;


import org.springframework.stereotype.Component;

@Component("testPreAuthorize")
public class TestPreAuthorize {
    public static boolean testPreAuth(){
        System.out.println("WORKED");
        return false;
    }
}