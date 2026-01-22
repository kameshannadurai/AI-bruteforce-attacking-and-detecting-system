package com.example.bruteforce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bruteforce.model.LoginAttempt;
import com.example.bruteforce.repository.LoginAttemptRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final LoginAttemptRepository repo;

    public AdminController(LoginAttemptRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/attempts")
    public List<LoginAttempt> allAttempts() {
        return repo.findAll();
    }
}
