package com.rest.template.config;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author gaoruiqiang
 * @version 1.0
 * @description: TODO
 * @date 2023/6/9 17:57
 */
public class jiemi {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("oneapm");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("1q2w3e!QW#E");
        //加密后数据
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        //对加密后内容解密
        String decrypt = textEncryptor.decrypt(username);
        String decrypt1 = textEncryptor.decrypt("KwwpK365a3/RYURcQ57Hg8YIH73HZ4EI");
        System.out.println(decrypt);
        System.out.println(decrypt1);
    }
}
