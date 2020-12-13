package com.tutorial.exception;

public class ErrorResponse {
	private String message;
	private String description;
	private String endPoint;

	public ErrorResponse(String message, String description, String endPoint) {
		super();
		this.message = message;
		this.description = description;
		this.endPoint = endPoint;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
}
