package com.mini.autorizador.autorizador.utils;

import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@UtilityClass
public class Encript {

    public static String encript(String senha){
        return Base64.getEncoder().encodeToString(senha.getBytes());
    }
    public static String decript(String senha){
        var senhaArray =  Base64.getDecoder().decode(senha);
        return new String(senhaArray, StandardCharsets.UTF_8);
    }
}
