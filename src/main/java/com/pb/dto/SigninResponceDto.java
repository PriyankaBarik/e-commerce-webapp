package com.pb.dto;

public class SigninResponceDto {
	private String status;
	private String token;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public SigninResponceDto(String status, String token) {
		super();
		this.status = status;
		this.token = token;
	}


}
