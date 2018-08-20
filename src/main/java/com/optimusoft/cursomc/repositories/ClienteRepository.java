package com.optimusoft.cursomc.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	Page<Cliente> findAll(Pageable pageRequest);
	
	@Transactional
	Cliente findByEmail(String email);

}
