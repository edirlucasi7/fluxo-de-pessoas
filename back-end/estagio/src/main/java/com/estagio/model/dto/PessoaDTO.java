package com.estagio.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.estagio.model.Pessoa;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class PessoaDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String cpf; 
	private String email;
	
	public PessoaDTO(Pessoa p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.cpf = p.getCpf();
		this.email = p.getEmail();
	}
	
}
