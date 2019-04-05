package com.sl.demo.server.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 微信加密
 */
public class Decript {

    public static String SHA1 (String decript){

        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为16进制数
            for (int i = 0 ; i < messageDigest.length; i++){
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if(shaHex.length() < 2){
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            result = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
