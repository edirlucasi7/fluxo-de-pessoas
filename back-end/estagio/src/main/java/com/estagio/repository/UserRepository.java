package com.estagio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estagio.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByLogin(String login);
	
}
