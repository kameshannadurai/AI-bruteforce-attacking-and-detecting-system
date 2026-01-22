package com.example.bruteforce.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import com.example.bruteforce.dto.LoginRequest;
import com.example.bruteforce.model.Student;
import com.example.bruteforce.repository.StudentRepository;
import com.example.bruteforce.service.AiSecurityService;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final StudentRepository repo;
    private final AiSecurityService ai;
    private final PasswordEncoder encoder;

    public AuthController(StudentRepository repo,
                          AiSecurityService ai,
                          PasswordEncoder encoder) {
        this.repo = repo;
        this.ai = ai;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody LoginRequest request,
            HttpServletRequest servletRequest) {

        String ip = servletRequest.getRemoteAddr();

        if (ai.isBlocked(ip)) {
            return Map.of(
                    "status", "BLOCKED",
                    "message", "Too many failed attempts"
            );
        }

        Student s = repo.findByRegNo(request.getRegNo());
        boolean success = s != null &&
                encoder.matches(request.getPassword(), s.getPassword());

        ai.record(ip, success);

        return success
                ? Map.of("status", "SUCCESS", "message", "Login successful")
                : Map.of("status", "FAILED", "message", "Invalid credentials");
    }
    @PostMapping("/reset-password")
    public String resetPassword() {

        Student s = repo.findByRegNo("RA2411003020915");

        if (s == null) {
            return "Student not found";
        }

        // 🔐 SET NEW PASSWORD HERE
        s.setPassword(encoder.encode("Kamesh@2006"));

        repo.save(s);

        return "Password reset successful. New password is:Kamesh@2006";
    }
    @PostMapping("/create-users")
    public String createUsers() {

        createUser("RA2411003020909", "Rino@2006");
        createUser("RA2411003020935", "Ashwin@2006");
        createUser("RA2411003020930", "Nithish@2006");

        return "3 users created successfully";
    }

    private void createUser(String regNo, String rawPassword) {

        // ✅ Password rule
        if (rawPassword.length() < 6) {
            throw new RuntimeException("Password too short for " + regNo);
        }

        // ✅ Unique user check
        if (repo.findByRegNo(regNo) != null) {
            System.out.println("User already exists: " + regNo);
            return;
        }

        Student s = new Student();
        s.setRegNo(regNo);
        s.setPassword(encoder.encode(rawPassword)); 

        repo.save(s);
    }



}
