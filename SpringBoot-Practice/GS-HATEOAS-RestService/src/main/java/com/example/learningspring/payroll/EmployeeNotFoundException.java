package com.example.learningspring.payroll;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {
	
	EmployeeNotFoundException(long id){
		super("Could not found employee: " + id);
	}

}
