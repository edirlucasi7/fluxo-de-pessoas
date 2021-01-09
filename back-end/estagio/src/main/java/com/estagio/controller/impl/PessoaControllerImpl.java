package com.estagio.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	@PostMapping("/cadastrar")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa p) throws PessoaException {
		service.salvarPessoa(p);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	

	@Override
	@GetMapping("/buscarPessoa/{primeiroNome}")
	public ResponseEntity<Pessoa> getPessoaNome(@PathVariable("primeiroNome") String primeiroNome) throws PessoaException {
		Pessoa pessoaNome = service.buscarPessoaNome(primeiroNome);
		
		if (pessoaNome != null) {
			return ResponseEntity.ok(pessoaNome);
		} else {
		
		return ResponseEntity.notFound().build();
		}
	}

	@Override
	@GetMapping("/listar")
	public ResponseEntity<List<PessoaDTO>> getPessoa() throws PessoaException {
		List<PessoaDTO> pessoas = service.listarPessoas();
		
		return pessoas.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(pessoas);
	}
	
	@Override
	@GetMapping("/quantidadeVisitantes")
	public ResponseEntity<String> getPessoaVisitantes() throws PessoaException {
		int quantidadeVisitantes = service.informarQuantidadeVisitantes();
		
		return ResponseEntity.ok("Quantidade de visitantes:" + quantidadeVisitantes);
	}

	@Override
	@GetMapping("/listar/{papeis}")
	public ResponseEntity<List<Pessoa>> getPessoaVisitante(@PathVariable("papeis") Papel papeis) throws PessoaException {
		List<Pessoa> visitantes = service.listarPessoaPorPapel(papeis);
		
		return visitantes.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(visitantes);
		
	}

	@Override
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<Pessoa> deletarPessoa(@PathVariable("id") Long id) throws PessoaException{
		service.removerPorId(id); 
		
		return ResponseEntity.ok().build();
	}

	@Override
	@PutMapping("/atualizarStatus/{primeiroNome}")
	public ResponseEntity<Pessoa> alterarStatusSaida(@PathVariable("primeiroNome") String nome) throws PessoaException {
		Pessoa pessoaExiste = service.alterarStatus(nome);
		System.out.println(pessoaExiste);
		
		if (pessoaExiste != null) {
			return ResponseEntity.ok(pessoaExiste);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}


	@Override
	@GetMapping("/quantidadePorPapel")
	public ResponseEntity<String> getPessoasVisitantesFuncionarios() throws PessoaException {
		String quantidadePessoasPorPapel = service.quantidadePessoaPorPapel();
		return ResponseEntity.ok(quantidadePessoasPorPapel);
	}

	@Override
	@GetMapping("/listarPessoa/{id}")
	public ResponseEntity<Pessoa> getPessoaPorId(@PathVariable("id") Long id) throws PessoaException {
		Pessoa pessoa = service.buscarPorId(id);
		return ResponseEntity.ok(pessoa);
	}

	@Override
	@GetMapping("/infoUser")
	public UserDetails getPessoaUsuario(@AuthenticationPrincipal UserDetails user) throws PessoaException {
		return user;
	}


	@Override
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody JwtLoginInput login) throws PessoaException {
		return ResponseEntity.ok().build();
	}
	
}
