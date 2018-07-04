package com.optimusoft.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.optimusoft.cursomc.enums.EstadoPagamento;
import com.optimusoft.cursomc.enums.TipoCliente;
import com.optimusoft.cursomc.models.Categoria;
import com.optimusoft.cursomc.models.Cidade;
import com.optimusoft.cursomc.models.Cliente;
import com.optimusoft.cursomc.models.Endereco;
import com.optimusoft.cursomc.models.Estado;
import com.optimusoft.cursomc.models.ItemPedido;
import com.optimusoft.cursomc.models.Pagamento;
import com.optimusoft.cursomc.models.PagamentoComBoleto;
import com.optimusoft.cursomc.models.PagamentoComCartao;
import com.optimusoft.cursomc.models.Pedido;
import com.optimusoft.cursomc.models.Produto;
import com.optimusoft.cursomc.services.CategoriaService;
import com.optimusoft.cursomc.services.CidadeService;
import com.optimusoft.cursomc.services.ClienteService;
import com.optimusoft.cursomc.services.EnderecoService;
import com.optimusoft.cursomc.services.EstadoService;
import com.optimusoft.cursomc.services.ItemPedidoService;
import com.optimusoft.cursomc.services.PagamentoService;
import com.optimusoft.cursomc.services.PedidoService;
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

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private ItemPedidoService itemPedidoService;

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

		SimpleDateFormat sdf = new SimpleDateFormat("DD/mm/yyyy");

		Pedido ped1 = new Pedido(null, sdf.parse("01/07/2018"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("03/07/2018"), cli1, e2);

		PagamentoComCartao pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);

		PagamentoComBoleto pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,
				sdf.parse("20/07/2018"), null);

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		// cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		List<Categoria> listaCategoria = Arrays.asList(cat1, cat2);
		List<Produto> listaProduto = Arrays.asList(p1, p2, p3);
		List<Estado> listaEstado = Arrays.asList(est1, est2);
		List<Cidade> listaCidade = Arrays.asList(c1, c2, c3);
		List<Cliente> listaCliente = Arrays.asList(cli1);
		List<Endereco> listaEndereco = Arrays.asList(e1, e2);
		List<Pedido> listaPedido = Arrays.asList(ped1, ped2);
		List<Pagamento> listaPagamento = Arrays.asList(pagto1, pagto2);
		List<ItemPedido> listaItemPedido = Arrays.asList(ip1, ip2, ip3);

		categoriaService.gravarLista(listaCategoria);
		produtoService.gravarLista(listaProduto);
		estadoService.gravarLista(listaEstado);
		cidadeService.gravarLista(listaCidade);
		clienteService.gravarLista(listaCliente);
		enderecoService.gravarLista(listaEndereco);
		pedidoService.gravarLista(listaPedido);
		pagamentoService.gravarLista(listaPagamento);
		itemPedidoService.gravarLista(listaItemPedido);

	}
}
