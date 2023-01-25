package com.mycompany.webapp.exception;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler
	public String method(NullPointerException exep) {
		return "error/xxx1";
	}
	
	@ExceptionHandler
	public String method(NumberFormatException exep) {
		return "error/xxx2";
	}
	
	
	@ExceptionHandler
	public String method(SQLException exep) {
		return "error/xxx3";
	}
}
