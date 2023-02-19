package com.minicampus.minicampus.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {

    public static boolean equals(String password , String hashedPassword){
        if(password == null || password.length() < 1){
            return false;
        }

        if(hashedPassword == null || hashedPassword.length() < 1){
            return false;
        }
        return BCrypt.checkpw(password , hashedPassword);
    }

    public static String encPassword(String password){
        if(password == null || password.length() < 1){
            return "";
        }
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
