package com.mini.autorizador.autorizador.exceptions;

import lombok.Getter;

@Getter
public class CartaoExistenteException extends RuntimeException{

    public CartaoExistenteException(String msg){
        super(msg);
    }

}
