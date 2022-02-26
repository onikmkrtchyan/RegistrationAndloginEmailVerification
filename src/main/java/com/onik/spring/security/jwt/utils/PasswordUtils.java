package com.onik.spring.security.jwt.utils;


import com.onik.spring.security.jwt.dtos.request.CreatePasswordUserDTO;
import com.onik.spring.security.jwt.exception.PasswordMismatchException;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;


public class PasswordUtils {

    public static String generate() {
        return RandomStringUtils.randomAlphanumeric(15);
    }

    public static String getDisfiguredPassword(String password) {
        byte[] passwordArr = password.substring(0, 10).getBytes(StandardCharsets.UTF_8);
        passwordArr = Base64.getEncoder().encode(passwordArr);
        Arrays.sort(passwordArr);
        return Base64.getEncoder().encodeToString(passwordArr);
    }

    public static void comparePasswords(CreatePasswordUserDTO createPasswordUserDTO) {
        if (createPasswordUserDTO.getPassword().equals(createPasswordUserDTO.getRepeatedPassword()))
            return;
        throw new PasswordMismatchException();
    }
}
