package com.api.pessoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.pessoa.model.Pessoa;
import com.api.pessoa.repository.PessoaRepository;
import com.api.pessoa.repository.filter.PessoaFilter;
import com.api.pessoa.service.exception.PessoaNaoEncontradaException;

@Service
public class PessoaService {
	
	@Autowired 
	PessoaRepository repository;
	
	public Pessoa salvar(Pessoa pessoa) {
		incluirTelefones(pessoa);
		return repository.save(pessoa);
	}
	
	public Pessoa buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada."));
	}
	
	public Pessoa atualizar(Pessoa pessoa) {
		Pessoa pessoaExistente = buscarPorId(pessoa.getId());
		pessoaExistente = removerTelefones(pessoa, pessoaExistente);
		pessoaExistente = incluirTelefones(pessoa);
		pessoa.setId(pessoa.getId());
		
		return repository.save(pessoaExistente);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Page<Pessoa> pesquisar(PessoaFilter filter, Pageable pageable) {
		return repository.pesquisar(filter, pageable);
	}

	private Pessoa incluirTelefones(Pessoa pessoa) {
		if (pessoa.getTelefones() != null && !pessoa.getTelefones().isEmpty()) {
			pessoa.getTelefones().forEach(telefone -> {
				telefone.setPessoa(pessoa);
			});
		}
		
		return pessoa;		
	}
	
	private Pessoa removerTelefones(Pessoa pessoa, Pessoa pessoaExistente) {
		if(pessoaExistente.getTelefones() != null && !pessoaExistente.getTelefones().isEmpty()) {
			pessoaExistente.getTelefones().removeIf(t -> !pessoa.getTelefones().contains(t));
		}
		
		return pessoaExistente;
	}

}
