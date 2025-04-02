package com.challenge.itau.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public record TransacaoDTO(
        @NotNull @DecimalMin(value = "0.0", inclusive = true) Double valor,
        @NotNull OffsetDateTime dataHora
) {}