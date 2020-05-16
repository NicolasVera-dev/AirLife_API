package com.airlife.wsAirLife.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IntrouvableException extends RuntimeException{
	public IntrouvableException(String error) {
		super(error);
	}
}
