package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clienteRepository extends JpaRepository<cliente, Long> {

}
