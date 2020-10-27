package com.estagio.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.estagio.model.enums.Papel;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private static int quantidadeMaxVisitantes = 10;
	
	private String nome;
	private String cpf; 
	private String email;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_entrada")
	private LocalDate dataEntrada;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_saida")
	private LocalDate dataSaida;
	
	@Enumerated(EnumType.STRING)
	private Papel papel;
	
	public static int getQuantidadeMaxVisitantes() {
		return quantidadeMaxVisitantes;
	}

}
