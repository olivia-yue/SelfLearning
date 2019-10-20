package com.example.learningspring.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CUSTOME_ORDER")
public class Order {
	
	private @Id @GeneratedValue long id;
	private String description;
	private Status status;
	
	public Order(){}
	
	public Order(String description, Status status){
		this.description = description;
		this.status = status;
	}

}
