package com.util;

import java.util.Random;

/**
 * 生成token
 */
public class Token {
    private final static String tokenBase= "abcdefghijklmnopqrstuvwsyz1234567890";
    public String token="";
    public Token() {
        Random rand = new Random();
        for(int i =0 ;i <32;i++){
            token+=tokenBase.charAt(rand.nextInt(32));
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
