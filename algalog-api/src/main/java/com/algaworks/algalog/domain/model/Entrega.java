package com.algaworks.algalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Embedded //abstrair os dados para outra classe mapeando a mesma classe
    private Destinatario destinatario;

    private BigDecimal taxa;

    @Enumerated(EnumType.STRING) //String para armazenar na coluna o próprio status descrito
    private StatusEntrega status;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;

}
