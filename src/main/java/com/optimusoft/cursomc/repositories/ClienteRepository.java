package com.optimusoft.cursomc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

}
