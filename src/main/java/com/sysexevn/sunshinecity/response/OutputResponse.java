package com.sysexevn.sunshinecity.response;

import java.util.ArrayList;
import java.util.List;

public class OutputResponse<T> {
	
	private String message;
	private List<T> data = new ArrayList<>();
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

	
	
}
