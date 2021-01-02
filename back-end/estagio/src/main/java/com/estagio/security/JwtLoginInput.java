package com.estagio.security;

import lombok.Data;

@Data
public class JwtLoginInput {

	private String username;
	private String password;

}
