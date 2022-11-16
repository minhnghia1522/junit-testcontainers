package com.sysexevn.sunshinecity.exception;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import com.sysexevn.sunshinecity.exception.error.Error;

public class NotAcceptableException extends BaseException {

	private static final long serialVersionUID = -2038010054163944964L;

	/**
	 * Constructor.
	 */
	public NotAcceptableException() {
		super(new Error("not_acceptable",
				"The requested resource cannot be processed because the user is not accepted to process the request."),
				HttpStatus.NOT_ACCEPTABLE);
	}

	public NotAcceptableException(MessageSource messageSource, String code, String[] param) {
		super(new Error(code, messageSource.getMessage(code, param, Locale.US)), HttpStatus.NOT_ACCEPTABLE);
	}

}
