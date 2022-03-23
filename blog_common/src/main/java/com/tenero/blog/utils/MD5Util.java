package com.tenero.blog.utils;




import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author kesong
 * @date 2022/3/14 14:31
 */
public class MD5Util {
    /**
     * @param data 明文
     * @return 密文大写
     */
    public static String md5UpperCase(String data) {
        return DigestUtils.md5Hex(data).toUpperCase();
    }

    /**
     * @param data 明文
     * @return 密文小写
     */
    public static String md5LowerCase(String data) {
        return DigestUtils.md5Hex(data).toLowerCase();
    }

    /**
     * MD5方法
     *
     * @param text 明文
     * @param salt 盐
     * @return 密文
     */
    public static String md5UpperCase(String text, String salt) {
        byte[] bytes = (text + salt).getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        messageDigest.update(bytes);
        bytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) < 0x10) {
                sb.append("0");
            }
            sb.append(Long.toString(bytes[i] & 0xff, 16));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * MD5方法
     *
     * @param text 明文
     * @param salt 盐
     * @return 密文
     */
    public static String md5LowerCase(String text, String salt) {
        byte[] bytes = (text + salt).getBytes(StandardCharsets.UTF_8);

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        messageDigest.update(bytes);
        bytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) < 0x10) {
                sb.append("0");
            }
            sb.append(Long.toString(bytes[i] & 0xff, 16));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param salt 盐
     * @param md5  密文
     * @return true/false
     */
    public static boolean verify(String text, String salt, String md5) {
        String md5Text = md5UpperCase(text, salt);
        System.out.println(md5Text.equalsIgnoreCase(md5));
        return md5Text.equalsIgnoreCase(md5);
    }

    /**
     * md5, 16位，小写
     *
     * @param text
     * @param salt
     * @return
     */
    public static String md516LowerCase(String text, String salt) {
        return md5LowerCase(text, salt).substring(8, 24);
    }

    /**
     * md5, 16位，大写
     *
     * @param text
     * @param salt
     * @return
     */
    public static String md516UpperCase(String text, String salt) {
        return md5UpperCase(text, salt).substring(8, 24);
    }

    public static String md516UpperCase(String text) {
        return md5UpperCase(text, "").substring(8, 24);
    }

}
