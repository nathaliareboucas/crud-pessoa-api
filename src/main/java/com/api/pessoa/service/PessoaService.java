package com.api.pessoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.pessoa.model.Pessoa;
import com.api.pessoa.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired PessoaRepository repository;
	
	public Pessoa salvar(Pessoa pessoa) {
		incluirTelefones(pessoa);
		return repository.save(pessoa);
	}

	private void incluirTelefones(Pessoa pessoa) {
		if (pessoa.getTelefones() != null && !pessoa.getTelefones().isEmpty()) {
			pessoa.getTelefones().forEach(telefone -> {
				telefone.setPessoa(pessoa);
			});
		}
		
	}

}
