package com.algaworks.algalog.dto;

import com.algaworks.algalog.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@Setter
public class EntregaModelDTO {

    private Long id;
    private ClienteResumoModelDTO cliente;
    private DestinatarioModelDTO destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalização;



}
