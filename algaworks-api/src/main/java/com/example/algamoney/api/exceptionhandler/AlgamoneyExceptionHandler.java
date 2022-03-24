package com.example.algamoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource; 
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemUsusario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale() );
		String mensagemDesenvolvedor = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(mensagemUsusario, mensagemDesenvolvedor) ,  headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	public static class Erro {
		
			private String mensagemUsusario;
			private String mensagemDesenvolvedor;
			
			
			public Erro(String mensagemUsusario, String mensagemDesenvolvedor) {
				this.mensagemUsusario = mensagemUsusario;
				this.mensagemDesenvolvedor = mensagemDesenvolvedor;
			}


			public String getMensagemUsusario() {
				return mensagemUsusario;
			}


			public String getMensagemDesenvolvedor() {
				return mensagemDesenvolvedor;
			}
		
	}

}
