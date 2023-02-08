package com.mycompany.webapp.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
	
	private static Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404, 요청 url을 찾을 수 없음
	public String runtimeExceptionHandler(Exception e) {
		logger.info("exception : " + e.getMessage());
		return "jsp/error/runtimeexception";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400, 요청에 문제가 있기 때문에 서버에서 인식할 수 없음
	public String numberFormmatExceptionHandler(Exception e) {
		logger.info("exception : " + e.getMessage());
		return "jsp/error/numberformatexception";
	}

	@ExceptionHandler({Exception.class, NullPointerException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500, 서버에서 처리시 문제가 발생(프로그램 내부적인 오류)
	public String exceptionHandler(Exception e) {
		logger.info("exception : " + e.getMessage());
		return "jsp/error/exception";
	}
	
}
