package com.account.photo.utils;

import java.util.Random;

public class Utils {
    public static String passwordGenerator() {
        Random random = new Random();
        StringBuilder pass = new StringBuilder();
        for(int i=0; i<10; i++)
            pass.append((char) (random.nextInt(43) + 48));
        return pass.toString();
    }
}
