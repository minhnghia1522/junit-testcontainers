package com.sysexevn.sunshinecity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {
	private String token;
	private String type;

	public JwtResponse(String token) {
		super();
		this.token = token;
		this.type = "Bearer";
	}

}
