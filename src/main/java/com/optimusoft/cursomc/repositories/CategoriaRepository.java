package com.optimusoft.cursomc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer>{

}
