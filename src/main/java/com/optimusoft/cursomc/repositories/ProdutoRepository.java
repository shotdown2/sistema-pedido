package com.optimusoft.cursomc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}
