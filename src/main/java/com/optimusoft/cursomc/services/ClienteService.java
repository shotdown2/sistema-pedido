package com.optimusoft.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimusoft.cursomc.models.Cliente;
import com.optimusoft.cursomc.repositories.ClienteRepository;
import com.optimusoft.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public void gravar(Cliente cliente) {
		repository.save(cliente);
	}
	
	public void gravarLista(List<Cliente> lista) {
		repository.saveAll(lista);
	}
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
