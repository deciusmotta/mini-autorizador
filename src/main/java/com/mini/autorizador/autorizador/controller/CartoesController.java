package com.mini.autorizador.autorizador.controller;

import com.mini.autorizador.autorizador.DTO.CartaoDTO;
import com.mini.autorizador.autorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequestMapping(value = "/cartoes")
public class CartoesController {

    @Autowired
    CartaoService service;

    @PostMapping
    public ResponseEntity<CartaoDTO> postCartao(@Valid @RequestBody CartaoDTO cartaoDTO){
        var response = Optional.ofNullable(service.salvarNovoCartao(cartaoDTO))
                .orElse(null);
            if(response == null)return ResponseEntity.status(422).body(cartaoDTO);
            else return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{numero}")
    public ResponseEntity<Double> getSaldo(@PathVariable String numero) {
        var response = Optional.ofNullable(service.getSaldo(numero))
                .orElse(null);
        if(response == null) return ResponseEntity.status(404).body(null);
        else return ResponseEntity.ok(response);
    }

}
