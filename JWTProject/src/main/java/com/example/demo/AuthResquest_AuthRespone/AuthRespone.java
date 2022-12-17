package com.example.demo.AuthResquest_AuthRespone;

public class AuthRespone {
	private String notification;
	private String accesstoken;
	
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public AuthRespone(String notification, String accesstoken) {
		this.notification = notification;
		this.accesstoken = accesstoken;
	}
	public AuthRespone() {
	
	}
	
}
