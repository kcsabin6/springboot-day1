package com.rab3tech.rest.controller;

public class AppResponse {
	
	private int code;
	private String mesage;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMesage() {
		return mesage;
	}
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	@Override
	public String toString() {
		return "AppResponse [code=" + code + ", mesage=" + mesage + "]";
	}
	
	
	
	

}
