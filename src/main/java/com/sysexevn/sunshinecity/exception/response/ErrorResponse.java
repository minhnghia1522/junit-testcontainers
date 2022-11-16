package com.sysexevn.sunshinecity.exception.response;

import org.springframework.http.HttpStatus;

import com.sysexevn.sunshinecity.exception.BaseException;
import com.sysexevn.sunshinecity.exception.error.Error;

public class ErrorResponse extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1488871523951031287L;

	public ErrorResponse(String code, String message) {
		super(new Error(code, message), HttpStatus.OK);

	}
}
