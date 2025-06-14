package com.projeto.examinado.Utils;

import org.apache.commons.codec.digest.DigestUtils;


import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordHasher {


    public static String encode(String stringHash){
        return DigestUtils.sha3_224Hex(stringHash);
    }


}
