package com.optimusoft.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

	Page<Categoria> findAll(Pageable pageRequest);

}
