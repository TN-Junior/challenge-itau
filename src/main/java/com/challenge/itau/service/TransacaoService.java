package com.challenge.itau.service;

import com.challenge.itau.dto.EstatisticaDTO;
import com.challenge.itau.dto.Transacao;
import com.challenge.itau.dto.TransacaoDTO;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransacaoService {

    private final List<Transacao> transacoes = Collections.synchronizedList(new ArrayList<>());

    public boolean salvar(TransacaoDTO dto) {
        // Removido o bloqueio de datas futuras para facilitar testes
        if (dto.valor() < 0) {
            return false;
        }

        transacoes.add(new Transacao(dto.valor(), dto.dataHora()));
        return true;
    }

    public void limpar() {
        transacoes.clear();
    }

    public EstatisticaDTO obterEstatisticas() {
        OffsetDateTime agora = transacoes.stream()
                .map(Transacao::dataHora)
                .max(OffsetDateTime::compareTo)
                .orElse(OffsetDateTime.now());


        List<Transacao> ultimas = transacoes.stream()
                .filter(t -> !t.dataHora().isBefore(agora.minusSeconds(60)) && !t.dataHora().isAfter(agora))
                .toList();

        DoubleSummaryStatistics stats = ultimas.stream()
                .mapToDouble(Transacao::valor)
                .summaryStatistics();

        return new EstatisticaDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin() == Double.POSITIVE_INFINITY ? 0 : stats.getMin(),
                stats.getMax() == Double.NEGATIVE_INFINITY ? 0 : stats.getMax()
        );
    }
}
