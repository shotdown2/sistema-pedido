package com.optimusoft.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

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

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

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
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		// cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		List<Categoria> listaCategoria = Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7);
		List<Produto> listaProduto = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);
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
