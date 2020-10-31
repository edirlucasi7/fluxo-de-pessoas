package com.estagio.model.enums;

public enum Status {

	ENTROU_NA_JFRN("Entrou_na_jfrn"), SAIU_DA_JFRN("Saiu_da_jfrn");
	private String descricao;

	private Status(String valor) {
		descricao = valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
