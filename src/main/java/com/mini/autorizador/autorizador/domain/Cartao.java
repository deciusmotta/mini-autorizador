package com.mini.autorizador.autorizador.domain;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Proxy(lazy=false)
@Table(name = "cartao")
public class Cartao implements Serializable {
    @Id
    private String numeroCartao;
    private String senha;
    private Double saldo;
}
