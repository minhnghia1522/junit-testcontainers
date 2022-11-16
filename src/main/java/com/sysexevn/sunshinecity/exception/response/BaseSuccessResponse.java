package com.sysexevn.sunshinecity.exception.response;

import org.springframework.http.HttpStatus;

import com.sysexevn.sunshinecity.exception.success.Success;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BaseSuccessResponse extends RuntimeException {

	private static final long serialVersionUID = 3822630927323371288L;

	@Getter
	private HttpStatus status = HttpStatus.OK;
	@Getter
	private Success success;

	public BaseSuccessResponse(Success success, HttpStatus status) {
		this.success = success;
		this.status = status;
	}

	public BaseSuccessResponse(String message) {
		super(message);
	}

	public BaseSuccessResponse(Throwable cause) {
		super(cause);
	}
}
