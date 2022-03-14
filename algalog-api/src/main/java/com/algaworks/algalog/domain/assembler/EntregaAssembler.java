package com.algaworks.algalog.domain.assembler;

import com.algaworks.algalog.domain.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.dto.EntregaModelDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class EntregaAssembler {

    private ModelMapper modelMapper;

    public EntregaModelDTO toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModelDTO.class);
    }

    public List<EntregaModelDTO> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }

}
