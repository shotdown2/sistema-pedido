package com.optimusoft.cursomc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.optimusoft.cursomc.models.Categoria;
import com.optimusoft.cursomc.models.Cidade;
import com.optimusoft.cursomc.models.Estado;
import com.optimusoft.cursomc.models.Produto;
import com.optimusoft.cursomc.services.CategoriaService;
import com.optimusoft.cursomc.services.CidadeService;
import com.optimusoft.cursomc.services.EstadoService;
import com.optimusoft.cursomc.services.ProdutoService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private EstadoService estadoService;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		List<Categoria> listaCategoria = Arrays.asList(cat1, cat2);
		List<Produto> listaProduto = Arrays.asList(p1, p2, p3);
		List<Estado> listaEstado = Arrays.asList(est1, est2);
		List<Cidade> listaCidade = Arrays.asList(c1, c2, c3);

		categoriaService.gravarLista(listaCategoria);
		produtoService.gravarLista(listaProduto);
		estadoService.gravarLista(listaEstado);
		cidadeService.gravarLista(listaCidade);

	}
}
