package com.estagio.exception;

import org.springframework.http.HttpStatus;

public class PessoaException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private final String message;
	private final HttpStatus status;
	
	public PessoaException(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }
	
	public PessoaException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
	
	 @Override
	 public String getMessage() {
	     return message;
	 }

	 public HttpStatus getStatus() {
	     return status;
	 }
}
