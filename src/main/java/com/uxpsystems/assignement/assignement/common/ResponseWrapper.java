package com.uxpsystems.assignement.assignement.common;

public class ResponseWrapper {

	private String message;
	private Object data;
	
	public ResponseWrapper(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
