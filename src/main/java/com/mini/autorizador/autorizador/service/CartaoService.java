package com.mini.autorizador.autorizador.service;

import com.mini.autorizador.autorizador.DTO.CartaoDTO;
import com.mini.autorizador.autorizador.DTO.TransacaoDTO;
import com.mini.autorizador.autorizador.domain.Cartao;
import com.mini.autorizador.autorizador.exceptions.runtime.*;
import com.mini.autorizador.autorizador.repository.CartaoRespository;
import com.mini.autorizador.autorizador.utils.Encript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    CartaoRespository respository;

    public CartaoDTO salvarNovoCartao(CartaoDTO cartaoDTO){
            respository.findById(cartaoDTO.getNumeroCartao())
                    .orElseThrow(()-> new CartaoNaoExistenteException(cartaoDTO));
            return cartaoDTO;
    }

    public Double getSaldo(String numero) {
            return respository.findById(numero)
                    .orElseThrow(GetSaldoException::new).getSaldo();
    }

    public void transacao(TransacaoDTO dto) {
        var cartaoBuscado = new Cartao();
             cartaoBuscado = respository.findById(dto.getNumeroCartao())
                     .orElseThrow(TransacaoCartaoInexistenteException::new);
             if (!dto.senhaIgual(Encript.decript(cartaoBuscado.getSenha(),dto.getNumeroCartao()))){
                throw new SenhaDiferenteException();
             }
             var result = cartaoBuscado.getSaldo()-dto.getValor();
             if(result>=0) {
                 cartaoBuscado.setSaldo(result);
                 respository.save(cartaoBuscado);
             }else{
                 throw new SaldoInsuficienteException();
             }
    }

}
