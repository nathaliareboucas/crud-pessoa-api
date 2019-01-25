package com.api.pessoa.model;

public class Erro {

	private String mensagemUsuario;
	private String mensagemDesenvolvedor;
	private Long timestamp;

	public Erro(String mensagemUsuario, String mensagemDesenvolvedor, Long timestamp) {
		super();
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		this.timestamp = timestamp;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}

	public Long getTimestamp() {
		return timestamp;
	}

}
