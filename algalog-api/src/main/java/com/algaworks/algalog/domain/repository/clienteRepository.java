package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface clienteRepository extends JpaRepository<cliente, Long> {

    Optional<cliente> findByEmail(String email);

    }





















    //List<cliente> findByNome(String nome); metodo para buscar por nome
    //List<cliente> findByNomeContaining(String nome); metodo para buscar por letra/nome
