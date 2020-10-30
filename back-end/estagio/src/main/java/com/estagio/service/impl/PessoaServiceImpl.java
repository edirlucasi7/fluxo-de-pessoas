package com.estagio.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.enums.Papel;
import com.estagio.repository.PessoaRepository;
import com.estagio.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService{

	@Autowired
	private PessoaRepository rep;
	
	@Override
	public boolean salvarPessoa(Pessoa pessoa) throws PessoaException {
		LocalDate dataEntrada = LocalDate.now();
		
		Integer quantidadeVisitantes = rep.findAllPessoasVisitantes();
		
		if (quantidadeVisitantes == null) {
			quantidadeVisitantes = 0;
		} 
		
		if (pessoa.getPapeis().contains(Papel.VISITANTE)) {
			if (quantidadeVisitantes < Pessoa.getQuantidadeMaxVisitantes()) {
				pessoa.setDataEntrada(dataEntrada);
				
				rep.save(pessoa);
				
				return true;
			} else {
				return false;
			}
		} else {
			pessoa.setDataEntrada(dataEntrada);
			
			rep.save(pessoa);
			
			return true;
		}
				
	}

	@Override
	public List<Pessoa> listarPessoas() throws PessoaException {
		List<Pessoa> pessoas = rep.findAll();
		
		return pessoas;
	}
	
	@Override
	public String informarQuantidadeVisitantes() {
		Integer contVisitantes = rep.findAllPessoasVisitantes();
		System.out.println(contVisitantes);
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
	public boolean removerPorId(Long id) throws PessoaException {
		Optional<Pessoa> existePessoa = rep.findById(id);
		
		if (existePessoa.isPresent()) {
			rep.deleteById(id);
			
			return true;
		} else {
			return false;
		}
		
	}
	
	
}
