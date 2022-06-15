package com.mini.autorizador.autorizador.service;

import com.mini.autorizador.autorizador.DTO.CartaoDTO;
import com.mini.autorizador.autorizador.domain.Cartao;
import com.mini.autorizador.autorizador.repository.CartaoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    CartaoRespository respository;

    private static Double VALOR_INICIAL = 500.0;

    public CartaoDTO salvarNovoCartao(CartaoDTO cartaoDTO){
        var cartao = new Cartao();
        var senhaCriptografada = Base64.getEncoder().encodeToString(cartaoDTO.getSenha().getBytes());
        try {
             respository.findById(cartaoDTO.getNumeroCartao()).orElseThrow(Exception::new);
             return null;
        }catch (Exception ignored) {}
        cartao.setNumeroCartao(cartaoDTO.getNumeroCartao());
        cartao.setSenha(senhaCriptografada);
        cartao.setSaldo(VALOR_INICIAL);
        respository.save(cartao);
        var senhaArray = Base64.getDecoder().decode(senhaCriptografada);
        cartao.setSenha(new String(senhaArray, StandardCharsets.UTF_8));
        return new CartaoDTO(cartao);
    }
}
