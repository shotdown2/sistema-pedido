package com.optimusoft.cursomc.enums;

public enum Perfil {

	ADMIN ("ROLE	_ADMIN"), 
	CLIENTE ("ROLE_CLIENTE");
	
private final String tipoPerfil;
	
	private Perfil(String perfilSelecionado) {
		this.tipoPerfil = perfilSelecionado;
	}

	public String gettipoPerfil() {
		return tipoPerfil;
	}

}
