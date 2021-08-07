package com.estagio.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public String salva(Pessoa pessoa) throws PessoaException {	
		salvaStatus(pessoa);
		if (pessoa.getPapeis().contains(Papel.VISITANTE) && informaQuantidadeVisitantes() < pessoa.quantidadeMaxVisitantes()) {
			rep.save(pessoa);
			return "Usuário salvo com sucesso!";
		} 
		if (pessoa.getPapeis().contains(Papel.FUNCIONARIO)) {
			rep.save(pessoa);
			return "Usuário salvo com sucesso!";
		}
		return "Não foi possível salvar usuário!";
	} 
	
	@Override
	public void salvaStatus(Pessoa pessoa) {
		pessoa.setDataEntrada(LocalDateTime.now());
		pessoa.setStatus(Status.ENTROU_NA_JFRN);
	}

	@Override
	public Pessoa atualizaStatusSaida(Long id) throws PessoaException {
		Optional<Pessoa> optional = rep.findById(id);
		if (!optional.isPresent()) {
			throw new PessoaException("Não é pra chegar um id aqui sem que realmente esse objeto exista e esteja com status que entrou na jfrn");			
		}
		
		Pessoa pessoa = optional.get();	
		pessoa.setDataSaida(LocalDateTime.now());
		pessoa.setStatus(Status.SAIU_DA_JFRN);
		rep.save(pessoa);
		return pessoa;
	}

	@Override
	public Pessoa buscaPessoa(Long id) throws PessoaException {
		Optional<Pessoa> pessoa = rep.findById(id);			
		if (!pessoa.isPresent()) {
			throw new PessoaException("Pessoa não existe");
		}
		return pessoa.get();		
	}

	@Override
	public List<PessoaDTO> listaPessoas() throws PessoaException {	
		return rep.findAll().stream().map(PessoaDTO::create).collect(Collectors.toList());		 
	}

	@Override
	public int informaQuantidadeVisitantes() {
		Integer contVisitantes = rep.findAllPessoasVisitantes();
		if (contVisitantes == null) {
			contVisitantes = 0;
		}		
		return contVisitantes;
	}

	@Override
	public List<Pessoa> listaPessoas(Papel papel) throws PessoaException {
		List<Pessoa> pessoas = rep.findByPapeis(papel);	
		return pessoas;
	}

	@Override
	public void remove(Long id) throws PessoaException {
		rep.deleteById(id);
	}

	@Override
	public String informaTodasPessoaPorPapel() throws PessoaException {
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

}
