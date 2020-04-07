package com.anz.ws.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.box.sdk.BoxAPIResponseException;

import io.jsonwebtoken.JwtException;

import com.anz.ws.response.model.ErrorResponse;

@Controller
@ControllerAdvice
public class APiCustomExceptionHandler {
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ErrorResponse> JwtException(JwtException ex, WebRequest request)
	{
		ErrorResponse errRes = new ErrorResponse();
		errRes.setTimestamp(new Date());
		errRes.setStatus("401");
		errRes.setError("Authorization Error");
		errRes.setMessage(ex.getMessage());
	    errRes.setLocation("Jwt");
	       
	    return new ResponseEntity<>(errRes, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		
	}
	
	@ExceptionHandler(Exception.class)
	  public ResponseEntity<ErrorResponse> handleServerException(Exception ex, WebRequest request) {
	    
		ErrorResponse errRes = new ErrorResponse();
		errRes.setTimestamp(new Date());
		errRes.setStatus("500");
		errRes.setError("Internal Server Error");
		errRes.setMessage(ex.getMessage());
	    errRes.setLocation("Api");
	       
	    return new ResponseEntity<>(errRes, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	

	
	@ExceptionHandler(BoxAPIResponseException.class)
	  public ResponseEntity<ErrorResponse> handleBoxAPIResponseException(Exception ex, WebRequest request) {
	    
		ErrorResponse errRes = new ErrorResponse();
		errRes.setTimestamp(new Date());
		errRes.setStatus("500");
		errRes.setError("Internal Server Error");
		errRes.setMessage(ex.getMessage());
	    errRes.setLocation("BoxApi");
	       
	    return new ResponseEntity<>(errRes, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	


}
