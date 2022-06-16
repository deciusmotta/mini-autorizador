package com.mini.autorizador.autorizador.service;

import com.mini.autorizador.autorizador.DTO.CartaoDTO;
import com.mini.autorizador.autorizador.DTO.TransacaoDTO;
import com.mini.autorizador.autorizador.ResponseEnum;
import com.mini.autorizador.autorizador.domain.Cartao;
import com.mini.autorizador.autorizador.repository.CartaoRespository;
import com.mini.autorizador.autorizador.utils.Encript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    CartaoRespository respository;

    private static Double VALOR_INICIAL = 500.0;

    public CartaoDTO salvarNovoCartao(CartaoDTO cartaoDTO){
        var cartao = new Cartao();
        var senhaCriptografada = Encript.encript(cartaoDTO.getSenha(),cartaoDTO.getNumeroCartao());
        try {
             buscarCartao(cartaoDTO.getNumeroCartao());
             return null;
        }catch (Exception ignored) {}
        cartao.setNumeroCartao(cartaoDTO.getNumeroCartao());
        cartao.setSenha(senhaCriptografada);
        cartao.setSaldo(VALOR_INICIAL);
        respository.save(cartao);
        cartao.setSenha(Encript.decript(senhaCriptografada,cartaoDTO.getNumeroCartao()));
        return new CartaoDTO(cartao);
    }

    public Double getSaldo(String numero) {
        try {
            return buscarCartao(numero).getSaldo();
        }catch (Exception ignored) {}
        return null;
    }

    public ResponseEnum transacao(TransacaoDTO dto) {
        var cartaoBuscado = new Cartao();
        try {
             cartaoBuscado = buscarCartao(dto.getNumeroCartao());
             if (dto.senhaIgual(Encript.decript(cartaoBuscado.getSenha(),dto.getNumeroCartao()))){
                 var result = cartaoBuscado.getSaldo()-dto.getValor();
                 if(result>=0) {
                     cartaoBuscado.setSaldo(result);
                     respository.save(cartaoBuscado);
                     return ResponseEnum.OK;
                 }else return ResponseEnum.SALDO_INSUFICIENTE;
             }else return ResponseEnum.SENHA_INVALIDA;
        }catch (Exception ignored) {}
        return ResponseEnum.CARTAO_INEXISTENTE;
    }

    private Cartao buscarCartao(String numero) throws Exception {
        return respository.findById(numero).orElseThrow(Exception::new);
    }
}
