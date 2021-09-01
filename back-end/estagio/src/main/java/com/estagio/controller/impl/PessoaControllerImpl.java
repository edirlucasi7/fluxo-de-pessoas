package com.estagio.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estagio.controller.PessoaController;
import com.estagio.exception.PessoaException;
import com.estagio.model.Pessoa;
import com.estagio.model.dto.PessoaDTO;
import com.estagio.model.enums.Papel;
import com.estagio.security.JwtLoginInput;
import com.estagio.service.PessoaService;

@RestController
@RequestMapping("api/v1")
public class PessoaControllerImpl implements PessoaController{

	@Autowired
	private PessoaService service;
	
	@Override
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/cadastrar")
	public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) throws PessoaException {
		service.salva(pessoa);
		return ResponseEntity.created(null).build();
	}
	
	@Override
	@GetMapping("/listarPessoa/{id}")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable("id") Long id) throws PessoaException {
		Pessoa pessoa = service.buscaPessoa(id);
		return ResponseEntity.ok(pessoa);
	}

	@Override
	@GetMapping("/listar")
	public ResponseEntity<List<PessoaDTO>> getPessoas() throws PessoaException {
		List<PessoaDTO> pessoas = service.listaPessoas();
		
		return pessoas.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(pessoas);
	}
	
	@Override
	@GetMapping("/qtdVisitantes")
	public ResponseEntity<String> getVisitantes() throws PessoaException {
		int quantidadeVisitantes = service.informaQuantidadeVisitantes();
		return ResponseEntity.ok("Quantidade de visitantes:" + quantidadeVisitantes);
	}

	@Override
	@GetMapping("/listar/{papeis}")
	public ResponseEntity<List<Pessoa>> getPessoas(@PathVariable("papeis") Papel papeis) throws PessoaException {
		List<Pessoa> pessoas = service.listaPessoas(papeis);
		return pessoas.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(pessoas);
	}

	@Override
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<Pessoa> deletaPessoa(@PathVariable("id") Long id) throws PessoaException{
		service.remove(id); 
		
		return ResponseEntity.ok().build();
	}

	@Override
	@PutMapping("/atualizarStatus/{id}")
	public ResponseEntity<Pessoa> alteraStatusSaida(@PathVariable("id") Long id) throws PessoaException {
		Pessoa pessoaExiste = service.atualizaStatusSaida(id);
		return ResponseEntity.ok(pessoaExiste);
	}

	@Override
	@GetMapping("/quantidadePorPapel")
	public ResponseEntity<String> getPessoasVisitantesFuncionarios() throws PessoaException {
		String quantidadePessoasPorPapel = service.informaTodasPessoaPorPapel();
		return ResponseEntity.ok(quantidadePessoasPorPapel);
	}

	@Override
	@GetMapping("/infoUser")
	public UserDetails getPessoaUsuario(@AuthenticationPrincipal UserDetails user) throws PessoaException {
		return user;
	}


	@Override
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtLoginInput login) throws PessoaException {
		return ResponseEntity.ok().build();
	}
	
}
