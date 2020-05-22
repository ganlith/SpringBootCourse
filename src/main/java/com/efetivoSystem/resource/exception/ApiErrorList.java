package com.efetivoSystem.resource.exception;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiErrorList extends ApiError {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> errors;

	public ApiErrorList(int code, String message, Date date, List<String> errors) {
		super(code, message, date);
		this.errors = errors;
	}

}