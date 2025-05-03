package com.z7i.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.z7i.erp.model.StudentRegistration;

public interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, Long> {
    Optional<StudentRegistration> findByRegistrationNumber(String registrationNumber);
}