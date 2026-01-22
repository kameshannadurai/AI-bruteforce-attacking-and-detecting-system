package com.example.bruteforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bruteforce.model.LoginAttempt;

import java.time.LocalDateTime;
import java.util.List;

public interface LoginAttemptRepository
        extends JpaRepository<LoginAttempt, Long> {

    List<LoginAttempt> findByIpAddressAndAttemptTimeAfter(
            String ip, LocalDateTime time);

    List<LoginAttempt> findByIpAddressAndSuccessAndAttemptTimeAfter(
            String ip, boolean success, LocalDateTime time);
}
