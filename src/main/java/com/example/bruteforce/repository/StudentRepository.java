package com.example.bruteforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bruteforce.model.Student;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Student findByRegNo(String regNo);
}
