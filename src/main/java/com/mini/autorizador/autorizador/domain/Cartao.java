package com.mini.autorizador.autorizador.domain;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Proxy(lazy=false)
@Table(name = "cartao")
public class Cartao {
    @Id
    @NotEmpty
    @Size(min=16,max=16,message = "VALOR COM 16 CARACTERES")
    private String numeroCartao;
    @NotEmpty
    private String senha;
    private Double saldo;
}
