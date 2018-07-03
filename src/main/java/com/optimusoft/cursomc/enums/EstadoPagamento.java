package com.optimusoft.cursomc.enums;

public enum EstadoPagamento {

	PENDENTE("Pendente"), 
	QUITADO("Quitado"), 
	CANCELADO("Cancelado");

	private final String estadoPagamento;

	private EstadoPagamento(String estadoSelecionado) {
		this.estadoPagamento = estadoSelecionado;
	}

	public String getEstadoPagamento() {
		return estadoPagamento;
	}

}
