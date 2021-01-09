package com.estagio.service;

import java.time.LocalDateTime;
import java.util.List;

import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.dto.PessoaDTO;
import com.estagio.model.enums.Papel;

public interface PessoaService {

	void salvarPessoa(Pessoa pessoa) throws PessoaException; 
	List<PessoaDTO> listarPessoas() throws PessoaException;
	int informarQuantidadeVisitantes() throws PessoaException;
	Pessoa buscarPorId(Long id) throws PessoaException;
	List<Pessoa> listarPessoaPorPapel(Papel papeis) throws PessoaException;
	void removerPorId(Long id) throws PessoaException;
	Pessoa buscarPessoaNome(String nome) throws PessoaException;
	Pessoa alterarStatus(String nome) throws PessoaException;
	String quantidadePessoaPorPapel() throws PessoaException;
	LocalDateTime dataEHoraAtual() throws PessoaException;
	
}
