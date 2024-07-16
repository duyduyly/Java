package com.alan.audit_jpa_lifecycle.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BookUtils {

    public int randomNumberBetween(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public String generateKey(int nText){
        StringBuffer key = new StringBuffer();
        for (int i = 0; i < nText; i++) {
            int rdNumber = this.randomNumberBetween(1, 125);
            key.append((char)rdNumber);
        }
        return key.toString();
    }
}
