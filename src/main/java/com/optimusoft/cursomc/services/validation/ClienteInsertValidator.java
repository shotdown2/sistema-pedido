package com.optimusoft.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.optimusoft.cursomc.controllers.exceptions.FieldMessage;
import com.optimusoft.cursomc.dto.ClienteNewDTO;
import com.optimusoft.cursomc.enums.TipoCliente;
import com.optimusoft.cursomc.models.Cliente;
import com.optimusoft.cursomc.repositories.ClienteRepository;
import com.optimusoft.cursomc.services.validation.utils.ValidaCpfECnpj;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA) && !ValidaCpfECnpj.isValidCpf(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido."));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA) && !ValidaCpfECnpj.isValidCnpj(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido."));
		}
		
		Cliente clienteAux = clienteRepository.findByEmail(objDto.getEmail());
		if(clienteAux != null) {
			list.add(new FieldMessage("email", "Email já cadastrado."));
		}
			
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}