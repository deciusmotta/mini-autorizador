package com.mini.autorizador.autorizador.controller;

import com.mini.autorizador.autorizador.DTO.TransacaoDTO;
import com.mini.autorizador.autorizador.ResponseEnum;
import com.mini.autorizador.autorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {

    @Autowired
    CartaoService service;

    @PostMapping
    public ResponseEntity<ResponseEnum> postTransacao(@Valid @RequestBody TransacaoDTO transacaoDTO) {
        var result = service.transacao(transacaoDTO);
        if(result.equals(ResponseEnum.OK)){
            return ResponseEntity.ok().body(ResponseEnum.OK);
        }else return ResponseEntity.status(422).body(result);
    }
}
