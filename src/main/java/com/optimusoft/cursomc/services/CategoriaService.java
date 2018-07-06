package com.optimusoft.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimusoft.cursomc.models.Categoria;
import com.optimusoft.cursomc.repositories.CategoriaRepository;
import com.optimusoft.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public void gravar(Categoria categoria) {
		repository.save(categoria);
	}

	public void gravarLista(List<Categoria> lista) {
		repository.saveAll(lista);
	}

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria update (Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	
}
