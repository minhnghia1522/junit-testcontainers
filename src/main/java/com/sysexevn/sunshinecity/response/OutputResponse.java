package com.sysexevn.sunshinecity.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputResponse<T> {
	
	private String message;
	private List<T> data = new ArrayList<>();
}
