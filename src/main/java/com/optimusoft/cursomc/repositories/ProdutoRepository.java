package com.optimusoft.cursomc.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimusoft.cursomc.models.Categoria;
import com.optimusoft.cursomc.models.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

	// @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat
	// WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	// Page<Produto> search(@Param("nome") String nome, @Param("categorias")
	// List<Categoria> categorias, Pageable pageRequest);

	@Transactional
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,
			Pageable pageRequest);

}
