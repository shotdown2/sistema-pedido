package com.optimusoft.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.optimusoft.cursomc.controllers.exceptions.FieldMessage;
import com.optimusoft.cursomc.dto.ClienteDTO;
import com.optimusoft.cursomc.enums.TipoCliente;
import com.optimusoft.cursomc.models.Cliente;
import com.optimusoft.cursomc.repositories.ClienteRepository;
import com.optimusoft.cursomc.services.validation.utils.ValidaCpfECnpj;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente clienteAux = clienteRepository.findByEmail(objDto.getEmail());
		if(clienteAux != null && !clienteAux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já cadastrado."));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA) && !ValidaCpfECnpj.isValidCpf(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido."));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA) && !ValidaCpfECnpj.isValidCnpj(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido."));
		}
		
			
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}