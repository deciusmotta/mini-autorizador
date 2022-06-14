package com.mini.autorizador.autorizador.controller;

import com.mini.autorizador.autorizador.DTO.CartaoDTO;
import com.mini.autorizador.autorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cartoes")
public class AutorizadorController {

    @Autowired
    CartaoService service;

    @PostMapping
    public ResponseEntity<CartaoDTO> postCartao(@Valid @RequestBody CartaoDTO cartaoDTO){
        System.out.println("UIII");
        var result =  service.salvarNovoCartao(cartaoDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<String> get(){
        System.out.println("OLOCO");
        return ResponseEntity.ok().body("HAHA");
    }
}
