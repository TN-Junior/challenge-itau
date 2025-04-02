package com.challenge.itau.controller;

import com.challenge.itau.dto.EstatisticaDTO;
import com.challenge.itau.service.TransacaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final TransacaoService transacaoService;

    public EstatisticaController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public EstatisticaDTO obter() {
        return transacaoService.obterEstatisticas();
    }
}
