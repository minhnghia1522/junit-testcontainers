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
import com.sysexevn.sunshinecity.exception.response.BaseSuccessResponse;
import com.sysexevn.sunshinecity.exception.success.Success;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Order(value = 1)
@Slf4j
public class BaseExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<Error> exceptions(BaseException exception) {
		log.error(exception.getMessage(), exception);
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
		log.error(exception.getMessage(), exception);
		return handleException(exception);
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Error> exceptions(HttpRequestMethodNotSupportedException exception) {
		log.error(exception.getMessage(), exception);
		return new ResponseEntity<Error>(new Error("method_not_allowed", "The requested method is not allowed."),
				HttpStatus.METHOD_NOT_ALLOWED);
	}

	private ResponseEntity<Error> handleException(Exception exception) {
		log.error(exception.getMessage(), exception);
		return new ResponseEntity<>(Error.builder().code("server_error").message(
				"An error occured on the server when processing the URL. Please contact the system administrator.")
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<Success> handleResponse(Exception exception) {
		log.error(exception.getMessage(), exception);
		return new ResponseEntity<>(Success.builder().code("server_error").message(
				"An error occured on the server when processing the URL. Please contact the system administrator.")
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = BaseSuccessResponse.class)
	public ResponseEntity<Success> exceptions(BaseSuccessResponse exception) {
		log.error(exception.getMessage(), exception);
		Success success = exception.getSuccess();
		if (ObjectUtils.isEmpty(success)) {
			return handleResponse(exception);
		}

		if (success.hasSuccess()) {
			return new ResponseEntity<Success>(success, exception.getStatus());
		}

		if (ObjectUtils.isEmpty(success.getMessage())) {
			String message = messageSource.getMessage(success.getCode(), success.getParams(), Locale.US);
			success.setMessage(message);
		}
		return new ResponseEntity<Success>(success, exception.getStatus());
	}

}
