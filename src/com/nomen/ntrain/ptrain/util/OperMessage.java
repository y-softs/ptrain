package com.nomen.ntrain.ptrain.util;

public class OperMessage {	
	private int    result;  //状态码
	private String message; //提示信息
	private String other;   //其他提示
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public int getResult() {
		return result;
	}
	
	public OperMessage(int result,String message,String other){
		this.result = result;
		this.message = message;
		this.other = other;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
