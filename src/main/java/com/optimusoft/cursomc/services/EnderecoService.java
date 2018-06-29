package com.optimusoft.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimusoft.cursomc.models.Endereco;
import com.optimusoft.cursomc.repositories.EnderecoRepository;
import com.optimusoft.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	public void gravar(Endereco endereco) {
		repository.save(endereco);
	}
	
	public void gravarLista(List<Endereco> lista) {
		repository.saveAll(lista);
	}
	
	public Endereco buscar(Integer id) {
		Optional<Endereco> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}

}
