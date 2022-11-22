package com.sysexevn.sunshinecity.exception.success;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import lombok.Builder;
import lombok.Data;

@Data
public class Success {
	private String code;
	private String message;
	private String[] params;
	private List<Success> success;

	/**
	 * Constructor.
	 *
	 * @param code    Success code
	 * @param message Success message
	 */
	@Builder
	public Success(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Constructor.
	 *
	 * @param code   Success code
	 * @param params parameter code
	 */
	public Success(String code, String[] params) {
		this.code = code;
		this.params = params;
	}

	/**
	 * Constructor.
	 *
	 * @param code Success code
	 */
	public Success(String code) {
		this.code = code;
	}

	/**
	 * Constructor.
	 *
	 * @param success List of Success
	 */
	public Success(List<Success> success) {
		this.success = success;
	}

	/**
	 * Add an Success to list.
	 *
	 * @param success Error
	 * @return current Error object
	 */
	public Success add(Success success) {
		if (CollectionUtils.isEmpty(this.success)) {
			this.success = new ArrayList<Success>();
		}
		this.success.add(success);
		return this;
	}

	public boolean hasSuccess() {
		return CollectionUtils.isEmpty(this.success) ? false : success.size() > 0;
	}
}
