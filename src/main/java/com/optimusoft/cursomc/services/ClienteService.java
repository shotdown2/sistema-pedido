package com.optimusoft.cursomc.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.optimusoft.cursomc.dto.ClienteDTO;
import com.optimusoft.cursomc.dto.ClienteNewDTO;
import com.optimusoft.cursomc.models.Cidade;
import com.optimusoft.cursomc.models.Cliente;
import com.optimusoft.cursomc.models.Endereco;
import com.optimusoft.cursomc.repositories.ClienteRepository;
import com.optimusoft.cursomc.repositories.EnderecoRepository;
import com.optimusoft.cursomc.services.exceptions.DataIntegrityException;
import com.optimusoft.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;

	public void gravar(Cliente cliente) {
		repository.save(cliente);
	}

	public void gravarLista(List<Cliente> lista) {
		repository.saveAll(lista);
	}

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return (List<Cliente>) repository.findAll();
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente que possui Pedidos");
		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {

		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), bCrypt.encode(objDto.getSenha()), objDto.getCpfOuCnpj(),
				objDto.getTipo(), objDto.getTelefone(), objDto.getCelular());

		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);

		Endereco endereco = new Endereco(null, objDto.getCep(), objDto.getLogradouro(), objDto.getNumero(),
				objDto.getComplemento(), objDto.getBairro(), cliente, cidade);
		cliente.getEnderecos().add(endereco);

		return cliente;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		return s3Service.uploadFile(multipartFile);
	}
}
