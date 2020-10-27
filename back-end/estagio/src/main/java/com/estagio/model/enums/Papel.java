package com.estagio.model.enums;

public enum Papel {
	
	FUNCIONARIO("Funcionario"), VISITANTE("Visitante");
	private String descricao;
	
	 Papel(String valor) {
		 descricao = valor;
	 }
	 
	 public String getDescricao() {
	        return descricao;
	 }

}
