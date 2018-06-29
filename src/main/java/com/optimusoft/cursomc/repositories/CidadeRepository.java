package com.optimusoft.cursomc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Cidade;

@Repository
public interface CidadeRepository extends CrudRepository<Cidade, Integer>{

}
