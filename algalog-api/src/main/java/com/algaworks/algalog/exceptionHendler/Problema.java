package com.algaworks.algalog.exceptionHendler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Problema {

    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;
    private List<campo> campos;

    @AllArgsConstructor
    @Getter
    public static class campo {

        private String nome;
        private String mensagem;
    }
}
