package com.z7i.erp.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.z7i.erp.dto.HallTicketResponse;
import com.z7i.erp.dto.PaymentRequest;
import com.z7i.erp.dto.StudentRegistrationRequest;
import com.z7i.erp.model.StudentRegistration;
import com.z7i.erp.service.StudentRegistrationServiceInterface;

@RestController
@RequestMapping("/api/student")
public class StudentRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(StudentRegistrationController.class);

    private final StudentRegistrationServiceInterface studentRegistrationService;

    public StudentRegistrationController(StudentRegistrationServiceInterface studentRegistrationService) {
        this.studentRegistrationService = studentRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationRequest request) {
        try {
            StudentRegistration student = studentRegistrationService.registerStudent(request);
            return ResponseEntity.ok(student);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            boolean success = studentRegistrationService.processPayment(paymentRequest);
            if (success) {
                return ResponseEntity.ok("Payment processed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment processing failed");
            }
        } catch (Exception e) {
            logger.error("Error in processPayment: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment processing failed due to error");
        }
    }

    @GetMapping("/hallticket/{registrationNumber}")
    public ResponseEntity<HallTicketResponse> getHallTicket(@PathVariable String registrationNumber) {
        try {
            Optional<HallTicketResponse> hallTicketOpt = studentRegistrationService.generateHallTicket(registrationNumber);
            if (hallTicketOpt.isPresent()) {
                return ResponseEntity.ok(hallTicketOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Error in getHallTicket: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
