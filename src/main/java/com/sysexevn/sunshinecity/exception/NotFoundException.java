package com.sysexevn.sunshinecity.exception;

import org.springframework.http.HttpStatus;

import com.sysexevn.sunshinecity.exception.error.Error;

public class NotFoundException extends BaseException {

	private static final long serialVersionUID = -6715574195087399663L;

	public NotFoundException() {
		super(new Error("not_found", "The request resource is not found"), HttpStatus.NOT_FOUND);
	}

	public NotFoundException(Throwable ex) {
		super(ex);
	}
	
}
