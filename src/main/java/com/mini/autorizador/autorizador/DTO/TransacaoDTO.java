package com.mini.autorizador.autorizador.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {

    private String numeroCartao;
    private String senhaCartao;
    private Double valor;

    public Double valorTransacao(Double valorTransacionado){
        var result =  this.valor - valorTransacionado;
        if (result < 0) return null;
        this.valor = result;
        return result;
    }

    public Boolean senhaIgual(String senhaPersistida){
        return this.senhaCartao.equals(senhaPersistida);
    }
}
