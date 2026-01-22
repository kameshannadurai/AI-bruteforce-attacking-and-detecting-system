package com.example.bruteforce.service;

import org.springframework.stereotype.Service;

@Service
public class RiskScoreService {

    public int calculateRisk(int failedAttempts, int totalAttempts) {

        if (failedAttempts >= 5) return 90;
        if (failedAttempts >= 3) return 70;
        if (failedAttempts >= 2) return 50;

        return 10;
    }
}
