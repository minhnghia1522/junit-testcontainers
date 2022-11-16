package com.sysexevn.sunshinecity.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sysexevn.sunshinecity.exception.error.Error;


@ControllerAdvice
@Order(value = 1)
public class BaseExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<Error> exceptions(BaseException exception) {
		Error err = exception.getError();

		if (ObjectUtils.isEmpty(err)) {
			return handleException(exception);
		}

		if (err.hasErrors()) {
			return new ResponseEntity<Error>(err, exception.getStatus());
		}

		if (ObjectUtils.isEmpty(err.getMessage())) {
			String message = messageSource.getMessage(err.getCode(), err.getParams(), Locale.US);
			err.setMessage(message);
		}

		return new ResponseEntity<Error>(err, exception.getStatus());
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Error> exceptions(Exception exception) {
		return handleException(exception);
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Error> exceptions(HttpRequestMethodNotSupportedException exception) {
		return new ResponseEntity<Error>(new Error("method_not_allowed", "The requested method is not allowed."),
				HttpStatus.METHOD_NOT_ALLOWED);
	}

	private ResponseEntity<Error> handleException(Exception exception) {
		// log.error("An error occured: ", exception);
		return new ResponseEntity<>(Error.builder().code("server_error").message(
				"An error occured on the server when processing the URL. Please contact the system administrator.")
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
