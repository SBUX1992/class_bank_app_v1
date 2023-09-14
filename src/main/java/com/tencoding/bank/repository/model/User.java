package com.tencoding.bank.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data	// Getter Setter 함께 선언
public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String fullname;
	private Timestamp createdAt;
	
	 
	
	
	
}
