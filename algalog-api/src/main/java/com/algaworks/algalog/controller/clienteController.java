package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.cliente;
import com.algaworks.algalog.domain.repository.clienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class clienteController {

    @Autowired
    private clienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<cliente> listar() {
        return clienteRepository.findAll();
    }
}
