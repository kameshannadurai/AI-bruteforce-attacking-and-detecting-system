package com.example.bruteforce.model;

import jakarta.persistence.*;


@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "reg_no")
    private String regNo;

    private String password;

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}

