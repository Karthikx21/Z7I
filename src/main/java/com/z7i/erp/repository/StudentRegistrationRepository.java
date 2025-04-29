package com.z7i.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.z7i.erp.model.StudentRegistration;

@Repository
public interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, Long> {
    Optional<StudentRegistration> findByRegistrationNumber(String registrationNumber);
}
