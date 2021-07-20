package com.carnauba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 2191626168358928835L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}
	
	
}
