package com.sysexevn.sunshinecity.dto;

import java.util.List;

import com.sysexevn.sunshinecity.exception.error.Error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDto<T> {

	private int code;
	private String message;
	private String[] params;
	private List<Error> errors;
	private T body;

	public ResponseDto(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
