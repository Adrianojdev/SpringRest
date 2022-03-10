package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.negocioException;
import com.algaworks.algalog.domain.model.cliente;
import com.algaworks.algalog.domain.repository.clienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class catalogoClienteService {

    private clienteRepository clienteRepository;

    @Transactional //declara que deve ser executado dentro de uma transação
    public cliente salvar(cliente cliente) {

        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                //esse metodo é utilizado tanto para adicionar como para atualizar
                .stream() //metodo para buscar se o email usado ja esta cadastrado
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if (emailEmUso) {
            throw new negocioException("Email já cadastrado");
        }
        return clienteRepository.save(cliente); //service para salvar cliente no banco
    }

    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId); //service para deletar cliente do banco
    }

}
