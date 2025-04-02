package com.challenge.itau.dto;

import java.time.OffsetDateTime;

public record Transacao(Double valor, OffsetDateTime dataHora) {}

