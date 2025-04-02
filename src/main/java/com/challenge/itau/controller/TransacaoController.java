package com.challenge.itau.controller;

import com.challenge.itau.dto.TransacaoDTO;
import com.challenge.itau.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> criarTransacao(@Valid @RequestBody TransacaoDTO dto) {
        boolean aceita = transacaoService.salvar(dto);
        return aceita ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limpar();
        return ResponseEntity.ok().build();
    }
}
