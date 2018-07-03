package com.optimusoft.cursomc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Pagamento;

@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Integer> {

}
