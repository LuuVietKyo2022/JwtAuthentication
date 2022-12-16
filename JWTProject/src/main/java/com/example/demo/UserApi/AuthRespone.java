package com.example.demo.UserApi;

public class AuthRespone {
	private String email;
	private String accesstoken;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public AuthRespone(String email, String accesstoken) {
		this.email = email;
		this.accesstoken = accesstoken;
	}
	public AuthRespone() {
	
	}
	
}
