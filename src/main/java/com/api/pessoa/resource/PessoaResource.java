package com.api.pessoa.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.pessoa.event.RecursoCriadoEvent;
import com.api.pessoa.model.Pessoa;
import com.api.pessoa.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService service;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = service.salvar(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this,response, pessoa.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

}