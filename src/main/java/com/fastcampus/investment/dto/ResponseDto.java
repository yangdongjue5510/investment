package com.fastcampus.investment.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseDto<T> {
	public ResponseDto(T data, HttpStatus status) {
		this.status = status;
		this.data = data;
	}

	HttpStatus status;
	T data;
}
