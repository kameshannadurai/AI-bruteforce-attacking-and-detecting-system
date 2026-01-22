package com.example.bruteforce.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;
    private boolean success;
    private LocalDateTime attemptTime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public LocalDateTime getAttemptTime() {
		return attemptTime;
	}
	public void setAttemptTime(LocalDateTime attemptTime) {
		this.attemptTime = attemptTime;
	}

   
}
