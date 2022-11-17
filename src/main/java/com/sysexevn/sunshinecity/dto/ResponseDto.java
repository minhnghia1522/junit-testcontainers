package com.sysexevn.sunshinecity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T> {

	 private T data;
	 private int code;
	 private String message;
}
