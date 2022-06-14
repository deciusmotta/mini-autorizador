package com.mini.autorizador.autorizador.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mini.autorizador.autorizador.domain.Cartao;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDTO {
    @JsonProperty(value="numeroCartao")
    private String numeroCartao;
    @JsonProperty(value="senha")
    private String senha;

    public CartaoDTO(Cartao cartao){
        numeroCartao = cartao.getNumeroCartao();
        senha = cartao.getSenha();
    }
}
