package com.account.photo.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Random;

public class Utils {
    public static String passwordGenerator() {
        Random random = new Random();
        StringBuilder pass = new StringBuilder();
        for(int i=0; i<10; i++)
            pass.append((char) (random.nextInt(43) + 48));
        return pass.toString();
    }

    public static boolean checkVkHash(long userId, long appId, String secretKey, String hash) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] genHash = md5.digest(String.join("", appId+"", userId+"", secretKey).getBytes(StandardCharsets.UTF_8));
        String resHash = HexFormat.of().formatHex(genHash);
        return resHash.equals(hash);
    }
}
