package com.optimusoft.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimusoft.cursomc.models.Estado;
import com.optimusoft.cursomc.repositories.EstadoRepository;
import com.optimusoft.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repository;
	
	public void gravar(Estado estado) {
		repository.save(estado);
	}
	
	public void gravarLista(List<Estado> lista) {
		repository.saveAll(lista);
	}
	
	public Estado buscar(Integer id) {
		Optional<Estado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}

}
