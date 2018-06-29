package com.optimusoft.cursomc.enums;

public enum TipoCliente {

	PESSOA_FISICA ("Pessoa Física"), 
	PESSOA_JURIDICA ("Pessoa Jurídica");
	
private final String tipoPessoa;
	
	private TipoCliente(String pessoaSelecionada) {
		this.tipoPessoa = pessoaSelecionada;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

}
