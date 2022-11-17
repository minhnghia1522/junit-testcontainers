package com.sysexevn.sunshinecity.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.sysexevn.sunshinecity.exception.error.Error;

@NoArgsConstructor
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 3822630927323371288L;

	@Getter
	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	@Getter
	private Error error;

	public BaseException(Error error, HttpStatus status) {
		this.error = error;
		this.status = status;
	}

	/**
	 * 
	 * @param message
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}
}
