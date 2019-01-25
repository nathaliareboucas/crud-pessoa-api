package com.api.pessoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.pessoa.model.Pessoa;
import com.api.pessoa.repository.PessoaRepository;
import com.api.pessoa.service.exception.PessoaNaoEncontradaException;

@Service
public class PessoaService {
	
	@Autowired PessoaRepository repository;
	
	public Pessoa salvar(Pessoa pessoa) {
		incluirTelefones(pessoa);
		return repository.save(pessoa);
	}
	
	public Pessoa buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada."));
	}

	private void incluirTelefones(Pessoa pessoa) {
		if (pessoa.getTelefones() != null && !pessoa.getTelefones().isEmpty()) {
			pessoa.getTelefones().forEach(telefone -> {
				telefone.setPessoa(pessoa);
			});
		}
		
	}

}
