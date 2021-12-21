package com.fastcampus.investment.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.investment.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseDto<String> handleException(Exception e) {
		return new ResponseDto<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
