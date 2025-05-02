package com.z7i.erp.service;

import java.util.Optional;

import com.z7i.erp.dto.HallTicketResponse;
import com.z7i.erp.dto.PaymentRequest;
import com.z7i.erp.dto.StudentRegistrationRequest;
import com.z7i.erp.model.StudentRegistration;

public interface StudentRegistrationServiceInterface {

    StudentRegistration registerStudent(StudentRegistrationRequest request);

    boolean processPayment(PaymentRequest paymentRequest);

    Optional<HallTicketResponse> generateHallTicket(String registrationNumber);
}
