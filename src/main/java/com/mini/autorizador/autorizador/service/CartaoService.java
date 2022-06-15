package com.mini.autorizador.autorizador.service;

import com.mini.autorizador.autorizador.DTO.CartaoDTO;
import com.mini.autorizador.autorizador.DTO.TransacaoDTO;
import com.mini.autorizador.autorizador.domain.Cartao;
import com.mini.autorizador.autorizador.repository.CartaoRespository;
import com.mini.autorizador.autorizador.utils.Encript;
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
        var senhaCriptografada = Encript.encript(cartaoDTO.getSenha());
        try {
             buscarCartao(cartaoDTO.getNumeroCartao());
             return null;
        }catch (Exception ignored) {}
        cartao.setNumeroCartao(cartaoDTO.getNumeroCartao());
        cartao.setSenha(senhaCriptografada);
        cartao.setSaldo(VALOR_INICIAL);
        respository.save(cartao);
        cartao.setSenha(Encript.decript(senhaCriptografada));
        return new CartaoDTO(cartao);
    }

    public Double getSaldo(String numero) throws Exception {
        try {
            return buscarCartao(numero).getSaldo();
        }catch (Exception ignored) {}
        return null;
    }

    public void transacao(TransacaoDTO dto) throws Exception {
        var cartaoBuscado = new Cartao();
        try {
             cartaoBuscado = buscarCartao(dto.getNumeroCartao());
             if (dto.senhaIgual(Encript.decript(cartaoBuscado.getSenha()))){
                 var result = cartaoBuscado.getSaldo()-dto.getValor();
                 if(result>0) {
                     cartaoBuscado.setSaldo(result);
                     respository.save(cartaoBuscado);
                 }
             }
        }catch (Exception ignored) {}
    }

    private Cartao buscarCartao(String numero) throws Exception {
        return respository.findById(numero).orElseThrow(Exception::new);
    }
}
