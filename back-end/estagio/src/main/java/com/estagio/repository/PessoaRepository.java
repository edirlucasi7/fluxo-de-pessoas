package com.estagio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estagio.model.Pessoa;
import com.estagio.model.enums.Papel;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	List<Pessoa> findByPapeis(Papel papeis);
	
	@Query(value = "select distinct count(pessoa_id) from papel as pa where pa.papel = 'VISITANTE'", nativeQuery = true)
	Integer findAllPessoasVisitantes();	

}
