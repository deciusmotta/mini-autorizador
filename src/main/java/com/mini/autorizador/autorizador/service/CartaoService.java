package com.mini.autorizador.autorizador.service;

import com.mini.autorizador.autorizador.DTO.CartaoDTO;
import com.mini.autorizador.autorizador.domain.Cartao;
import com.mini.autorizador.autorizador.repository.CartaoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CartaoService {

    @Autowired
    CartaoRespository respository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private static Double VALOR_INICIAL = 500.0;

    public CartaoDTO salvarNovoCartao(CartaoDTO cartaoDTO){
        var senhaCriptografada = passwordEncoder.encode(cartaoDTO.getSenha());
        var cartao = new Cartao();
        cartao.setNumeroCartao(cartaoDTO.getNumeroCartao());
        cartao.setSenha(senhaCriptografada);
        cartao.setSaldo(VALOR_INICIAL);
        return new CartaoDTO(cartao);
    }

}
