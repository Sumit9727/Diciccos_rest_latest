package com.prakat.middleware.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionResponse {

	private LocalDateTime timeStamp ;
	private String message;
	private String details;
	private List<Map<String,String>> fieldErrors = new ArrayList<>();
	public ExceptionResponse(LocalDateTime timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	public ExceptionResponse() {
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public void addFieldError(String path, String message) {
        Map<String,String> error = new HashMap<>();
        error.put(path, message);
        fieldErrors.add(error);
    }

    public List<Map<String,String>> getFieldErrors() {
        return fieldErrors;
    }
}
