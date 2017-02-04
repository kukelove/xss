package com.xp.brushms.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * Created by hzm on 2015/7/10.
 */
public class CryptUtils {
    public static String getMd5(String raw){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = raw.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }

    public static byte[] base64Decode(String s) {
        try {
            return new sun.misc.BASE64Decoder().decodeBuffer(s);
        } catch (Exception exp) {
            return null;
        }
    }

    public static String xor(String orig, int code) {
        byte[] data = fromHexString(orig);
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte)(data[i] ^ code);
        }
        try {
            return new String(data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }

        return hexString.toString();
    }

    public static byte[] fromHexString(String hexstring) {
        byte[] inputByteArray = new byte[hexstring.length() / 2];
        for (int x = 0; x < hexstring.length() / 2; x++) {
            byte b = (byte) Integer.parseInt(
                    hexstring.substring(x * 2, x * 2 + 2), 16);
            inputByteArray[x] = b;
        }
        return inputByteArray;
    }
    public static byte[] aesEncrypt(byte[] byteContent, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            return aesEncryptByPasswordEnCode(byteContent, enCodeFormat);
        } catch(Exception exp) {
            exp.printStackTrace();;
            return null;
        }
    }
    public static byte[] aesEncryptByPasswordEnCode(byte[] byteContent, byte[] passwordEnCode) {
        try {
            byte[] enCodeFormat = passwordEnCode;
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] aesDecrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            return aesEncryptByPasswordEnCode(content, enCodeFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] aesDecryptByPasswordEnCode(byte[] content, byte[] passwordEncode) {
        try {
            byte[] enCodeFormat = passwordEncode;
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public final static String getFileMd5(String filePath) {
        FileInputStream fins = null;
        try {
            fins = new FileInputStream(filePath);
            return getMd5(fins);
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            if (fins != null) {
                try {
                    fins.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public final static String getMd5(InputStream ins) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            byte[] data = new byte[10240];
            while (true) {
                int len = ins.read(data);
                if (len <= 0) break;
                mdInst.update(data, 0, len);
            }
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
