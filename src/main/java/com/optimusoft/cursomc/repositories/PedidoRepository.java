package com.optimusoft.cursomc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

}
