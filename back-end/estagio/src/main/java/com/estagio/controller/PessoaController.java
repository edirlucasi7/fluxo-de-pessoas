package com.estagio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.dto.PessoaDTO;
import com.estagio.model.enums.Papel;

import io.swagger.annotations.ApiOperation;

public interface PessoaController {

	@ApiOperation(
			value = "Adiciona uma visitante/funcionario",
			notes = "Este controller é responsável por cadastrar um visitante/funcionário no sistema"
			)
	UserDetails getPessoaUsuario(@AuthenticationPrincipal UserDetails user) throws PessoaException;
	@ApiOperation(
			value = "Adiciona uma visitante/funcionario",
			notes = "Este controller é responsável por cadastrar um visitante/funcionário no sistema"
			)
	ResponseEntity<Pessoa> createPessoa(Pessoa p) throws PessoaException;
	
	@ApiOperation(
			value = "Busca uma pessoa",
			notes = "Este controller é responsável por buscar uma pessoa pelo nome no sistema"
			)
	ResponseEntity<Pessoa> getPessoaNome(String nome) throws PessoaException;
	

	@ApiOperation(
			value = "Busca uma pessoa por id",
			notes = "Este controller é responsável por buscar uma pessoa pelo id no sistema"
			)
	ResponseEntity<Pessoa> getPessoaPorId(Long id) throws PessoaException;

	@ApiOperation(
			value = "Lista todos as pessoas",
			notes = "Este controller é responsável por listar todas as pessoas cadastradas"
			)
	ResponseEntity<List<PessoaDTO>> getPessoa() throws PessoaException;
	
	@ApiOperation(
			value = "Lista as pessoas com papel de visitante",
			notes = "Este controller é responsável por listar a quantidade de pessoas com o papel viitante"
			)
	ResponseEntity<String> getPessoaVisitantes() throws PessoaException;
	
	@ApiOperation(
			value = "Lista as pessoas por papel",
			notes = "Este controller é responsável por listar a quantidade de pessoas por cada papel existente no sistema"
			)
	ResponseEntity<String> getPessoasVisitantesFuncionarios() throws PessoaException;
	
	@ApiOperation(
			value = "Lista as pessoas pelo papel",
			notes = "Este controller é responsável por listar as pessoas a partir de seu papel"	
			)
	ResponseEntity<List<Pessoa>> getPessoaVisitante(Papel papel) throws PessoaException;
	
	@ApiOperation(
			value = "Remove uma pessoa",
			notes = "Este controller é responsável por remover um visitante/funcionário do sistema"
			)
	ResponseEntity<Pessoa> deletarPessoa(Long id) throws PessoaException;
	
	@ApiOperation(
			value = "Alterar status de saida e insere data de saida",
			notes = "Este controller é responsável por alterar o status de saída de uma pessoa da JFRN juntamente com o horário atual"
			)
	ResponseEntity<Pessoa> alterarStatusSaida(String nome) throws PessoaException;
}
