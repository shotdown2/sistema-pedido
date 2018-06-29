package com.optimusoft.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimusoft.cursomc.models.Cidade;
import com.optimusoft.cursomc.repositories.CidadeRepository;
import com.optimusoft.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repository;
	
	public void gravar(Cidade cidade) {
		repository.save(cidade);
	}

	public void gravarLista(List<Cidade> lista) {
		repository.saveAll(lista);
	}
	
	public Cidade buscar(Integer id) {
		Optional<Cidade> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

}
