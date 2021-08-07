package com.estagio.service;

import java.util.List;

import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.dto.PessoaDTO;
import com.estagio.model.enums.Papel;

public interface PessoaService {

	String salva(Pessoa pessoa) throws PessoaException; 
	List<PessoaDTO> listaPessoas() throws PessoaException;
	int informaQuantidadeVisitantes() throws PessoaException;
	List<Pessoa> listaPessoas(Papel papeis) throws PessoaException;
	void remove(Long id) throws PessoaException;
	Pessoa buscaPessoa(Long id) throws PessoaException;
	Pessoa atualizaStatusSaida(Long id) throws PessoaException;
	void salvaStatus(Pessoa pessoa) throws PessoaException;
	String informaTodasPessoaPorPapel() throws PessoaException;
}
