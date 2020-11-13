package com.estagio.model.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.estagio.model.Pessoa;
import com.estagio.model.enums.Papel;

import lombok.Data;

@Data
public class PessoaDTO {

	private Long id;

	private String nome;
	private String cpf; 
	private String email;
	
	private List<Papel> papeis;
	
	public static PessoaDTO create(Pessoa p) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(p, PessoaDTO.class);
	}
	
}
