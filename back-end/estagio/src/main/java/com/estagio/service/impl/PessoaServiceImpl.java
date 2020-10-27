package com.estagio.service.impl;

import java.time.LocalDate;
import java.util.List;

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
	public void salvarPessoa(Pessoa pessoa) throws PessoaException {
		LocalDate dataEntrada = LocalDate.now();
		
		pessoa.setDataEntrada(dataEntrada);
		rep.save(pessoa);
	
	}

	@Override
	public List<Pessoa> listarPessoas() throws PessoaException {
		List<Pessoa> pessoas = rep.findAll();
		
		return pessoas;
	}

	@Override
	public List<Pessoa> listarVisitante(Papel papel) throws PessoaException {
		List<Pessoa> visitantes = rep.findByPapel(papel);
		
		return visitantes;
	}
	
	
}
