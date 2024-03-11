package com.rest.template.config;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author gaoruiqiang
 * @version 1.0
 * @description: TODO
 * @date 2023/6/9 17:57
 */
public class jiami {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(为上边配置文件中配置的密码)
        textEncryptor.setPassword("oneapm");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String  ss = "1q2w3e!QW#E";
        String password = textEncryptor.encrypt(ss);
        //加密后数据
        System.out.println("username:"+username);
        System.out.println("password:"+password);
//
//        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
//        String password2 = "1q2w3e!QW#E";
//        String encryptedPassword = encryptor.encryptPassword(password2);
//        System.out.println(encryptedPassword);

    }
}
