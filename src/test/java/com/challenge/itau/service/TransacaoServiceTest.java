package com.challenge.itau.service;

import com.challenge.itau.dto.EstatisticaDTO;
import com.challenge.itau.dto.TransacaoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoServiceTest {

    private TransacaoService service;

    @BeforeEach
    public void setUp() {
        service = new TransacaoService();
    }

    @Test
    public void deveSalvarTransacaoValida() {
        TransacaoDTO dto = new TransacaoDTO(100.0, OffsetDateTime.now().minusSeconds(10));

        boolean resultado = service.salvar(dto);

        assertTrue(resultado);
        EstatisticaDTO estatisticas = service.obterEstatisticas();
        assertEquals(1, estatisticas.count());
        assertEquals(100.0, estatisticas.sum());
        assertEquals(100.0, estatisticas.avg());
    }

    @Test
    public void deveRejeitarTransacaoComValorNegativo() {
        TransacaoDTO dto = new TransacaoDTO(-50.0, OffsetDateTime.now().minusSeconds(10));

        boolean resultado = service.salvar(dto);

        assertFalse(resultado);
    }

    @Test
    public void deveRejeitarTransacaoComDataFutura() {
        TransacaoDTO dto = new TransacaoDTO(10.0, OffsetDateTime.now().plusMinutes(5));

        boolean resultado = service.salvar(dto);

        assertFalse(resultado);
    }

    @Test
    public void deveLimparTransacoes() {
        TransacaoDTO dto = new TransacaoDTO(25.0, OffsetDateTime.now());
        service.salvar(dto);

        service.limpar();

        EstatisticaDTO estatisticas = service.obterEstatisticas();
        assertEquals(0, estatisticas.count());
        assertEquals(0.0, estatisticas.sum());
    }

    @Test
    public void deveIgnorarTransacoesForaDaJanelaDe60Segundos() {
        TransacaoDTO dto = new TransacaoDTO(99.0, OffsetDateTime.now().minusSeconds(90));
        service.salvar(dto);

        EstatisticaDTO estatisticas = service.obterEstatisticas();

        assertEquals(0, estatisticas.count());
    }
}
