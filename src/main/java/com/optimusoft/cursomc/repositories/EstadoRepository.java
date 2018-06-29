package com.optimusoft.cursomc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Estado;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer>{

}
