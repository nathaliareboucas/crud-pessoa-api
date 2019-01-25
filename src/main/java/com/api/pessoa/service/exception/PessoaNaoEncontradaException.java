package com.api.pessoa.service.exception;

public class PessoaNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException(String msg) {
		super(msg);
	}

}
