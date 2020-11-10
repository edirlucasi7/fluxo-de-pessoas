package com.estagio.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.estagio.exception.ObjectNotFoundException;
import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.dto.PessoaDTO;
import com.estagio.model.enums.Papel;
import com.estagio.model.enums.Status;
import com.estagio.repository.PessoaRepository;
import com.estagio.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService{

	@Autowired
	private PessoaRepository rep;
	
	@Override
	public boolean salvarPessoa(Pessoa pessoa) throws PessoaException {
		Assert.isNull(pessoa.getId(), "Não foi possível inserir o registro.");
		
		LocalDateTime dataEntrada = LocalDateTime.now();
				
		Integer quantidadeVisitantes = rep.findAllPessoasVisitantes();
		
		if (quantidadeVisitantes == null) {
			quantidadeVisitantes = 0;
		} 
		
		if (pessoa.getPapeis().contains(Papel.VISITANTE)) {
			if (quantidadeVisitantes < Pessoa.getQuantidadeMaxVisitantes()) {
				pessoa.setDataEntrada(dataEntrada);
				pessoa.setStatus(Status.ENTROU_NA_JFRN);
					
				rep.save(pessoa);
					
				return true;
			} else {
				return false;
			}
		} else {
			pessoa.setDataEntrada(dataEntrada);
			pessoa.setStatus(Status.ENTROU_NA_JFRN);
				
			rep.save(pessoa);
				
			return true;
		}
				
	}
	
	@Override
	public Pessoa buscarPessoaNome(String nome) throws PessoaException {
		Optional<Pessoa> pessoaOptional = rep.findByNome(nome);
		
		if(pessoaOptional.isPresent()) {
			Pessoa pessoa = pessoaOptional.get();
			
			return pessoa;
		}
		
		return null;

	}

	@Override
	public List<PessoaDTO> listarPessoas() throws PessoaException {
		
		return rep.findAll().stream().map(PessoaDTO::new).collect(Collectors.toList());
		 
	}
	
	@Override
	public String informarQuantidadeVisitantes() {
		Integer contVisitantes = rep.findAllPessoasVisitantes();

		if (contVisitantes == null) {
			contVisitantes = 0;	
		}
		
		return "A quantidade de visitantes é: " + contVisitantes;
	}

	@Override
	public List<Pessoa> listarPessoaPorPapel(Papel papeis) throws PessoaException {
		List<Pessoa> pessoas = rep.findByPapeis(papeis);
		
		return pessoas;
	}

	@Override
	public void removerPorId(Long id) throws PessoaException {
		rep.deleteById(id);
	}

	@Override
	public Pessoa alterarStatus(String nome) throws PessoaException {
		Optional<Pessoa> existePessoa = rep.findByNome(nome);
		
		if (existePessoa.isPresent()) {
			LocalDateTime dataSaida = LocalDateTime.now();
			Pessoa pessoa = existePessoa.get();
			
			pessoa.setStatus(Status.SAIU_DA_JFRN);
			pessoa.setDataSaida(dataSaida);
			
			rep.save(pessoa);
			
			return pessoa;
		}
		
		return null;
	}

	@Override
	public String quantidadePessoaPorPapel() throws PessoaException {
		Integer quantidadeVisitantes = rep.findAllPessoasVisitantes();
		Integer quantidadeFuncionarios = rep.findAllPessoasFuncionarios();
		
		if (quantidadeVisitantes == null) {
			quantidadeVisitantes = 0;
		}
		
		if (quantidadeFuncionarios == null) {
			quantidadeFuncionarios = 0;
		}
		
		return "A quantidade de funcionários é: " + quantidadeFuncionarios + " pessoas." + "\n"
				+ "A quantidade de visitantes é: " + quantidadeVisitantes + " pessoas.";
	}

	@Override
	public Pessoa buscarPorId(Long id) throws PessoaException {
		
		return rep.findById(id).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado."));
	}

}
