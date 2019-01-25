package com.api.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pessoa.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
