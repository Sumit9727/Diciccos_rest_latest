package com.prakat.middleware.exceptionhandler;

import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prakat.middleware.exception.BussinessException;
@ControllerAdvice
@RestController
public class CustomizedresponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BussinessException.class)
	public final ResponseEntity<Object> handleBussinessException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {	
		BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Invalid request", null);
        
		  for (org.springframework.validation.FieldError fieldError: fieldErrors) {
			  exceptionResponse.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
	        }
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	public final ResponseEntity<Object> handleToastClientException(HttpClientErrorException.NotFound ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(HttpClientErrorException.BadRequest.class)
	public final ResponseEntity<Object> handleToastClientException(HttpClientErrorException.BadRequest ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpServerErrorException.InternalServerError.class)
	public final ResponseEntity<Object> handleToastClientException(HttpServerErrorException.InternalServerError ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ConnectException.class)
	public final ResponseEntity<Object> handleToastClientException(ConnectException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
