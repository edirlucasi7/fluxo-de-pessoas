package com.estagio.service;

import java.util.List;

import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.enums.Papel;

public interface PessoaService {

	void salvarPessoa(Pessoa pessoa) throws PessoaException; 
	List<Pessoa> listarPessoas() throws PessoaException;
	List<Pessoa> listarVisitante(Papel papel) throws PessoaException;
}
