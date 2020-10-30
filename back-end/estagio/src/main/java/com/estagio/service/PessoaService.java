package com.estagio.service;

import java.util.List;

import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.enums.Papel;

public interface PessoaService {

	boolean salvarPessoa(Pessoa pessoa) throws PessoaException; 
	List<Pessoa> listarPessoas() throws PessoaException;
	String informarQuantidadeVisitantes() throws PessoaException;
	List<Pessoa> listarPessoaPorPapel(Papel papeis) throws PessoaException;
	boolean removerPorId(Long id) throws PessoaException;
}
