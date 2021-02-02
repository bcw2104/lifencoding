package com.lifencoding.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(java.lang.NumberFormatException.class)
	public void numberFormatException(NumberFormatException e, HttpServletResponse response) {
		try {
			response.sendError(404);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@ExceptionHandler(java.lang.Exception.class)
	public void exception(Exception e, HttpServletResponse response) {
		e.printStackTrace();
		try {
			response.sendError(500);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
