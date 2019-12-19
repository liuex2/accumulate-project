package com.cif.accumulate.util;

import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

    private static final String DEFAULT_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArromvW2N/rg0ADw9zpTLcGdO0wNazPcp+SepPrv1dicCamEVPfvPKlWMuYszt/tE6lNjMT8pphmatPvgjAFyKfE1fEpcvHqRSZTUtlo/fGJzh2nss6mxyDXlqi+sGitjwaGj6/MXO6zLQcMQmZ/UvliOhECvuLBsAqqLY8ik63Ah7ylWAap3jDD0OvgSy+glqebwfacy9WPYOy4K75n/DQRw9FJBYFg1BtfbVn55Oji3AZ0E3lY96b0JhJGtFM6vjF0bhVDkmP/XZINPcVZyxydRFvxjgA6we/KmxXDD/JdZmvGmrZ2XCAhGS3vuk3XJnkMquGYO4GAI13JIs8Z1rwIDAQAB";

    private static final String DEFAULT_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuuia9bY3+uDQAPD3OlMtwZ07TA1rM9yn5J6k+u/V2JwJqYRU9+88qVYy5izO3+0TqU2MxPymmGZq0++CMAXIp8TV8Sly8epFJlNS2Wj98YnOHaeyzqbHINeWqL6waK2PBoaPr8xc7rMtBwxCZn9S+WI6EQK+4sGwCqotjyKTrcCHvKVYBqneMMPQ6+BLL6CWp5vB9pzL1Y9g7Lgrvmf8NBHD0UkFgWDUG19tWfnk6OLcBnQTeVj3pvQmEka0Uzq+MXRuFUOSY/9dkg09xVnLHJ1EW/GOADrB78qbFcMP8l1ma8aatnZcICEZLe+6TdcmeQyq4Zg7gYAjXckizxnWvAgMBAAECggEBAIUx5KxMoo0FkKaJRbiAFyGFr0IOcZ6D4BxODqeDExhRnnbHsTDCD2e/NXqP0gozYpKapKf69V3ocNX8mG1CAOxWMyafbVhEuxJzgT/NRk4kotscdVN0TdFIvN3iX3jHkNEfzlzkv9Y+3+cusy3Y+I1t5p6MzSemXYAe0Alt/8J1QiRCF0BcStJWrfI0XZFZh1sVoh2IWRdwTkSODd2JZ9QWDV2yP5PsmGCu620W9hZ9l4u+qj2ulUHe4TYVzPSa8CguDm33eQqSarwbENR2XcNPefm4LqE0M2xzQpTvMqRfLnN04vQ8qutO2j3m2W0oxEr06Grk0RrFdjMKG/hHG2ECgYEA47WpQgKdRrrUjihZeg+naKfaQE9RDoM/QqSYhjIu9nGMIXsfdZGmjbPjemmaDTFpzALjlhk809+VrcSyapGQ1JboaTnmDwTQOlpFK41MVnb7RsUMVgB70x1Q/imdauA4KMyAfTbG/vf5V7wp3NUQTZm59BB5y9HHG2PgwItbtd8CgYEAxG9f/izVXzBIwZYhbH1FVMNiwNbEQh24Or3w/60+pqp5Z5rGL/BET9T9bWm54UYZudopfnK9Jzz9gOUf/6+U9nxEcvBYavmHtQdia30RUbbRnf+hyKxW7xiXI3M/hvwhmpERV6iDozKfSw7d2xllaQ9BOg83IbSo29zUCKFAGjECgYBacDMaOYmXpup2z4XCGeeGhGwFcLsjxXLhmBfP7KyEStK6FXmIK2H4E8M8QibiKIb5dQhI/BsRl1KOYnnuI4RfOtRxvoIl4uaE1od0Zbk0+iqQkAB3Q9fqIFGjsImKAiWr5pT9pjzg1UId22jRAXppJ64YRlbpYZbvxPpl3rN9BwKBgBGCpBnyQSr1EOdAzMXw/Mcri5gJDQGfyJZj7CG+fcE5nQzhPR1024IKSHUCYbF1IEHy8pP1AmuHAkoFIq8WJb87IJbmWdtfVvuKJvnR8kNIerMM4I0U38L8fCyKz3Tm8M/ocpVPzjwz/QMh1iD7yJBu7a+RP6bfhdXjf3stdJthAoGBAMQzXY1NI6vI55nDkKJdBVWpBzvo50QlbAS3gHW9Hbmy5A/0o8v2JVhRtkkhkr6KptYxlijVJjwPx5+b8lpo7arybiCBJCwn+pcYuTzuluOpBsRfPm+qSBP9tC8NG7ZKhZSVflxVtAjIfZsYLtVBON+gXDDqUiuv6ViZKBnF34rN";

    /**
     * 私钥
     */
    private static RSAPrivateKey privateKey;

    /**
     * 公钥
     */
    private static RSAPublicKey publicKey;

    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * 获取私钥
     * @return 当前的私钥对象
     */
    public static RSAPrivateKey getPrivateKey() {
        try {
            loadPrivateKey(DEFAULT_PRIVATE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * 获取公钥
     * @return 当前的公钥对象
     */
    public RSAPublicKey getPublicKey() {
        try {
            loadPublicKey(DEFAULT_PUBLIC_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * 随机生成密钥对
     */
    public void genKeyPair(){
        KeyPairGenerator keyPairGen= null;
        try {
            keyPairGen= KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGen.initialize(1024, new SecureRandom());
        KeyPair keyPair= keyPairGen.generateKeyPair();
        RSAUtil.privateKey= (RSAPrivateKey) keyPair.getPrivate();
        publicKey= (RSAPublicKey) keyPair.getPublic();
    }

    /**
     * 从文件中输入流中加载公钥
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(InputStream in) throws Exception{
        try {
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String readLine= null;
            StringBuilder sb= new StringBuilder();
            while((readLine= br.readLine())!=null){
                if(readLine.charAt(0)=='-'){
                    continue;
                }else{
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPublicKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }


    /**
     * 从字符串中加载公钥
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(String publicKeyStr) throws Exception{
        try {
            BASE64Decoder base64Decoder= new BASE64Decoder();
            byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
            RSAUtil.publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (IOException e) {
            throw new Exception("公钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     * @param in 私钥
     * @return 是否成功
     * @throws Exception
     */
    public void loadPrivateKey(InputStream in) throws Exception{
        try {
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String readLine= null;
            StringBuilder sb= new StringBuilder();
            while((readLine= br.readLine())!=null){
                if(readLine.charAt(0)=='-'){
                    continue;
                }else{
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPrivateKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    public static void loadPrivateKey(String privateKeyStr) throws Exception{
        try {
            BASE64Decoder base64Decoder= new BASE64Decoder();
            byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");
            privateKey= (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (IOException e) {
            throw new Exception("私钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 加密过程
     * @param publicKey 公钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception{
        if(publicKey== null){
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher= null;
        try {
            cipher= Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output= cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }


    /**
     * 解密过程
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception{
        if (privateKey== null){
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher= null;
        try {
            cipher= Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output= cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 解密过程
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(byte[] cipherData) throws Exception{
        if (privateKey== null){
            privateKey = getPrivateKey();
        }
        Cipher cipher= null;
        try {
            cipher= Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output= cipher.doFinal(cipherData);
            return new String(output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("密文数据已损坏");
        }
    }


    /**
     * 字节数据转十六进制字符串
     * @param data 输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data){
        StringBuilder stringBuilder= new StringBuilder();
        for (int i=0; i<data.length; i++){
            //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);
            //取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i<data.length-1){
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args){
        String s = "NNHbYMnQ2YyjIjtwmJfGLOqk64fQAlBTBLO0POuQTJLvJDL071gb26xHt6dvwpCnry9XfkekhrZRHlxlmv+AYMeje0mp5EqoYvIQRWpP96YMVi7B0FnmvJGPMTUW6iDR1j6M5ekDY8uR4OdHQgQrrTF9vgBR1nkqjhEfPRUxePWIlNTW53kEXscDz1w9TDD6+yBNqliqcB85PIRv0KsAGVks+w4KBDLMTAiAonRmD7n9YSNO8C8dxrNMhSPI/iasc0Bm7ECXyohbIVnb599l7X6vAYAoXM9fdXxBI3LXZe0rtpyKIJYEo4uTSy9PvS+9VviIboikXHGm6wNDY4ovpw==";
        try {
//            byte[] decrypt = decrypt(getPrivateKey(), Base64Util.decode(s));
//            System.out.println(new String(decrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }





       /* RSAUtil RSAUtil= new RSAUtil();
        //RSAUtil.genKeyPair();

        //加载公钥
        try {
            RSAUtil.loadPublicKey(RSAUtil.DEFAULT_PUBLIC_KEY);
            logger.info("加载公钥成功");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("加载公钥失败");
        }

        //加载私钥
        try {
            RSAUtil.loadPrivateKey(RSAUtil.DEFAULT_PRIVATE_KEY);
            logger.info("加载私钥成功");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("加载私钥失败");
        }

        //测试字符串
        String encryptStr= "123456";

        try {
            //加密
            byte[] cipher = RSAUtil.encrypt(RSAUtil.getPublicKey(), encryptStr.getBytes());
            String base64cipher = Base64Util.encode(cipher);
            logger.info(base64cipher);
            //解密
            byte[] plainText = RSAUtil.decrypt(RSAUtil.getPrivateKey(), Base64Util.decode(base64cipher));
            logger.info(new String(plainText));
            logger.info("密文长度:"+ cipher.length);
            logger.info(RSAUtil.byteArrayToString(cipher));
            logger.info("明文长度:"+ plainText.length);
            logger.info(RSAUtil.byteArrayToString(plainText));
            logger.info(new String(plainText));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }*/
    }
}