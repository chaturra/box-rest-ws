package com.anz.ws.response.model;

import java.util.Date;

public class ErrorResponse {
	
	private Date timestamp;
	private String message;
	private String status;
	private String error;
	private String location;
	
	public ErrorResponse() {};
	
	public ErrorResponse(Date timestamp,String message,String status,String error,String location)
	{
		this.timestamp=timestamp;
		this.message=message;
		this.status=status;
		this.error=error;
		this.location=location;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
