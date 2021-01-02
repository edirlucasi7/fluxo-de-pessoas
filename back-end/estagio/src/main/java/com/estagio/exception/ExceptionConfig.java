package com.estagio.exception;

import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.estagio.model.Pessoa;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({
		EmptyResultDataAccessException.class
	})
	public ResponseEntity<Pessoa> erroNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({
		IllegalArgumentException.class
	})
	public ResponseEntity<Pessoa> erroBadRequest(Exception ex) {
		return ResponseEntity.badRequest().build();
	}
	
//	@ExceptionHandler({
//		AccessDeniedException.class
//	})
//	public ResponseEntity<Object> acessoDenied() {
//		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionError("acesso negado"));
//	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<Object>(new ExceptionError("Método não suportado"), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	public class ExceptionError implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String error;
		ExceptionError(String error) {
			this.error = error;
		}
		
		public String getError() {
			return error;
		}	
	}
	
}
