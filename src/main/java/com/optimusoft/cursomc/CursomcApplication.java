package com.optimusoft.cursomc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.optimusoft.cursomc.enums.TipoCliente;
import com.optimusoft.cursomc.models.Categoria;
import com.optimusoft.cursomc.models.Cidade;
import com.optimusoft.cursomc.models.Cliente;
import com.optimusoft.cursomc.models.Endereco;
import com.optimusoft.cursomc.models.Estado;
import com.optimusoft.cursomc.models.Produto;
import com.optimusoft.cursomc.services.CategoriaService;
import com.optimusoft.cursomc.services.CidadeService;
import com.optimusoft.cursomc.services.ClienteService;
import com.optimusoft.cursomc.services.EnderecoService;
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
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EnderecoService enderecoService;

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

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "00011122233", TipoCliente.PESSOA_FISICA,
				"22223333", "99990000");
		
		Endereco e1 = new Endereco(null, "20530003", "Rua Freire", "200", "apto 203", "Jardim", cli1, c1);
		Endereco e2 = new Endereco(null, "20530003", "Rua Outra rua", "300", "apto 503", "Centro", cli1, c2);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		List<Categoria> listaCategoria = Arrays.asList(cat1, cat2);
		List<Produto> listaProduto = Arrays.asList(p1, p2, p3);
		List<Estado> listaEstado = Arrays.asList(est1, est2);
		List<Cidade> listaCidade = Arrays.asList(c1, c2, c3);
		List<Cliente> listaCliente = Arrays.asList(cli1);
		List<Endereco> listaEndereco = Arrays.asList(e1, e2);
		

		categoriaService.gravarLista(listaCategoria);
		produtoService.gravarLista(listaProduto);
		estadoService.gravarLista(listaEstado);
		cidadeService.gravarLista(listaCidade);
		clienteService.gravarLista(listaCliente);
		enderecoService.gravarLista(listaEndereco);
		

	}
}
