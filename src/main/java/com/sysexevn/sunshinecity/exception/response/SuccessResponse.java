package com.sysexevn.sunshinecity.exception.response;

import org.springframework.http.HttpStatus;

import com.sysexevn.sunshinecity.exception.success.Success;

public class SuccessResponse extends BaseSuccessResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5247803692851133488L;

	public SuccessResponse(String code, String message) {
		super(new Success(code, message), HttpStatus.OK);
	}
}
