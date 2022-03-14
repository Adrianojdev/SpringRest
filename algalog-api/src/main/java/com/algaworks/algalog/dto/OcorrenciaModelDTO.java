package com.algaworks.algalog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
@Setter
public class OcorrenciaModelDTO {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;

}
