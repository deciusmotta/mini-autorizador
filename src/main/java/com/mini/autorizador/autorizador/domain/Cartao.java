package com.mini.autorizador.autorizador.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "person")
public class Cartao {
    @Id
    @NotEmpty
    @Size(min=16,max=16)
    private String numeroCartao;
    @NotEmpty
    private String senha;
    private Double saldo;
}
