package com.api.pessoa.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.pessoa.model.Pessoa;
import com.api.pessoa.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	
	Page<Pessoa> pesquisar(PessoaFilter filter, Pageable pageable);

}
