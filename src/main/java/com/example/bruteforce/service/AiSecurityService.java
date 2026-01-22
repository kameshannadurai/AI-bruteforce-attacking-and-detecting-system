package com.example.bruteforce.service;

import com.example.bruteforce.model.LoginAttempt;
import com.example.bruteforce.repository.LoginAttemptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AiSecurityService {

    private final LoginAttemptRepository repo;

    private static final int LIMIT = 3;

    public AiSecurityService(LoginAttemptRepository repo) {
        this.repo = repo;
    }

    public boolean isBlocked(String ip) {

        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);

        int failedAttempts = repo
                .findByIpAddressAndSuccessAndAttemptTimeAfter(
                        ip, false, oneMinuteAgo
                ).size();

        return failedAttempts >= LIMIT;
    }

    public void record(String ip, boolean success) {

        LoginAttempt attempt = new LoginAttempt();
        attempt.setIpAddress(ip);
        attempt.setSuccess(success);
        attempt.setAttemptTime(LocalDateTime.now());
        repo.save(attempt);

        if (!success) {
            System.out.println("🚨 AI ALERT: Failed login from IP " + ip);
        }
    }
}
