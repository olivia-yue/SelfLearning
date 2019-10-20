package com.example.learningspring.payroll;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {
	
	OrderNotFoundException(long id){
		super("Could not found order: " + id);
	}

}
