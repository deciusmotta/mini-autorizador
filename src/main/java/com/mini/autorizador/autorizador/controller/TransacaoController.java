package com.mini.autorizador.autorizador.controller;

import com.mini.autorizador.autorizador.DTO.CartaoDTO;
import com.mini.autorizador.autorizador.DTO.TransacaoDTO;
import com.mini.autorizador.autorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {

    @Autowired
    CartaoService service;

    @PostMapping
    public ResponseEntity<TransacaoDTO> postTransacao( @RequestBody TransacaoDTO transacaoDTO) throws Exception {
        service.transacao(transacaoDTO);
        return ResponseEntity.noContent().build();
    }
}
