package com.api.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pessoa.model.Pessoa;
import com.api.pessoa.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{

}
