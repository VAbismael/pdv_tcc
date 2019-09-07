package com.herby.pdv_tcc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.herby.pdv_tcc.domain.enums.TipoCliente;
import com.herby.pdv_tcc.dto.ClienteNewDTO;
import com.herby.pdv_tcc.resources.exceptions.FieldMessage;
import com.herby.pdv_tcc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	@Override
	public void initialize(ClienteInsert ann) {		
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCnpjOuCpf())) {
			list.add(new FieldMessage("cnpjOuCpf", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCnpjOuCpf())) {
			list.add(new FieldMessage("cnpjOuCpf", "CNPJ inválido"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
		
	}

}
