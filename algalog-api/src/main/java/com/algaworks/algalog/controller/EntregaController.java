package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.assembler.EntregaAssembler;
import com.algaworks.algalog.domain.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import com.algaworks.algalog.dto.EntregaModelDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModelDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @GetMapping
    public List<EntregaModelDTO> listar() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModelDTO> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)//corpo da requisição é um objeto do tipo entrega
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());




//                EntregaModelDTO entregaModelDTO = new EntregaModelDTO();
//                entregaModelDTO.setId(entrega.getId());
//                entregaModelDTO.setNomeCliente(entrega.getCliente().getNome());
//                entregaModelDTO.setDestinatario(new DestinatarioModel());
//                entregaModelDTO.getDestinatario().setNome(entrega.getDestinatario().getNome());
//                entregaModelDTO.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
//                entregaModelDTO.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
//                entregaModelDTO.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
//                entregaModelDTO.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
//                entregaModelDTO.setTaxa(entrega.getTaxa());
//                entregaModelDTO.setStatus(entrega.getStatus());
//                entregaModelDTO.setDataPedido(entrega.getDataPedido());
//                entregaModelDTO.setDataFinalização(entrega.getDataFinalizacao());


                 //se não exister nada dentro do optional retornado pelo findById retorna vazio
    }// modelo de representação do recurso

}
