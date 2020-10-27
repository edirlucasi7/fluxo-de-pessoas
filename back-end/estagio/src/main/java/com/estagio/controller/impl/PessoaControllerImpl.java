package com.estagio.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estagio.controller.PessoaController;
import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.enums.Papel;
import com.estagio.service.PessoaService;

@RestController
@RequestMapping("api/v1/pessoa")
public class PessoaControllerImpl implements PessoaController{

	@Autowired
	private PessoaService service;
	
	@Override
	@PostMapping
	public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa p) throws PessoaException {
		service.salvarPessoa(p);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/listar")
	public ResponseEntity<List<Pessoa>> getPessoa() throws PessoaException {
		List<Pessoa> pessoas = service.listarPessoas();
		
		return pessoas.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(pessoas);
	}

	@Override
	@GetMapping("/listar/{papel}")
	public ResponseEntity<List<Pessoa>> getPessoaVisitante(@PathVariable("papel") Papel papel) throws PessoaException {
		List<Pessoa> visitantes = service.listarVisitante(papel);
		
		return visitantes.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(visitantes);
		
	}
	
}
