package com.optimusoft.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimusoft.cursomc.models.ItemPedido;
import com.optimusoft.cursomc.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repository;

	public void gravar(ItemPedido itemPedido) {
		repository.save(itemPedido);
	}

	public void gravarLista(List<ItemPedido> lista) {
		repository.saveAll(lista);
	}

	public ItemPedido buscar(Integer id) {
		Optional<ItemPedido> obj = repository.findById(id);
		return obj.orElse(null);
	}

}
