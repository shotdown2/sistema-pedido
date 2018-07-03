package com.optimusoft.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimusoft.cursomc.models.Pagamento;
import com.optimusoft.cursomc.repositories.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;

	public void gravar(Pagamento pagamento) {
		repository.save(pagamento);
	}

	public void gravarLista(List<Pagamento> lista) {
		repository.saveAll(lista);
	}

	public Pagamento buscar(Integer id) {
		Optional<Pagamento> obj = repository.findById(id);
		return obj.orElse(null);
	}

}
