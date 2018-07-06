package com.optimusoft.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimusoft.cursomc.models.Pedido;
import com.optimusoft.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public void gravar(Pedido pedido) {
		repository.save(pedido);
	}

	public void gravarLista(List<Pedido> lista) {
		repository.saveAll(lista);
	}

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElse(null);
	}

}
