package com.sysexevn.sunshinecity.exception.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error {

	private String code;
	private String message;
	private String[] params;
	private List<Error> errors;

	/**
	 * Constructor.
	 *
	 * @param code    Error code
	 * @param message Error message
	 */
	@Builder
	public Error(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Constructor.
	 *
	 * @param code   Error code
	 * @param params parameter code
	 */
	public Error(String code, String[] params) {
		this.code = code;
		this.params = params;
	}

	/**
	 * Constructor.
	 *
	 * @param code Error code
	 */
	public Error(String code) {
		this.code = code;
	}

	/**
	 * Constructor.
	 *
	 * @param errors List of Error
	 */
	public Error(List<Error> errors) {
		this.errors = errors;
	}

	/**
	 * Add an Error to list.
	 *
	 * @param error Error
	 * @return current Error object
	 */
	public Error add(Error error) {
		if (CollectionUtils.isEmpty(this.errors)) {
			this.errors = new ArrayList<Error>();
		}
		this.errors.add(error);
		return this;
	}

	public boolean hasErrors() {
		return CollectionUtils.isEmpty(this.errors) ? false : errors.size() > 0;
	}
}
