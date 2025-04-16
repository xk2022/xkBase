package com.xk.common.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * 📌 自定義業務異常
 */
@Getter
public class BusinessException extends RuntimeException {

	private final Object status;

	public BusinessException(String message) {
		super(message);
		this.status = HttpStatus.BAD_REQUEST;
	}

	public BusinessException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

}
