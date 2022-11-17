package com.sysexevn.sunshinecity.exception;

import org.springframework.http.HttpStatus;
import com.sysexevn.sunshinecity.exception.error.Error;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2617040708856849963L;

	/**
	 * Constructor
	 * 
	 * @param error
	 */
	public BadRequestException(Error error) {
		super(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Constructor
	 * 
	 * @param code
	 */
	public BadRequestException(String code) {
		super(new Error(code), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Constructor
	 * 
	 * @param code
	 * @param params
	 */
	public BadRequestException(String code, String[] params) {
		super(new Error(code, params), HttpStatus.BAD_REQUEST);
	}

}
