package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class clienteController {

    @GetMapping("/clientes")
    public List<cliente> listar() {

        cliente cliente1 = new cliente();
        cliente1.setId(1L);
        cliente1.setNome("Joao");
        cliente1.setEmail("joao.21@gmail.com");
        cliente1.setTelefone("47 - 3325-5847");

        cliente cliente2 = new cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria");
        cliente2.setEmail("maria@gmail.com");
        cliente2.setTelefone("47 - 3340-5547");

        return Arrays.asList(cliente1, cliente2);
    }
}
