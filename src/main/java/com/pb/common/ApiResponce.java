package com.pb.common;

import java.time.LocalDateTime;

public class ApiResponce {

	private final boolean success;
	private final String message;

	public ApiResponce(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

    public String getTimestamp() {
    	return LocalDateTime.now().toString();
    }
}
