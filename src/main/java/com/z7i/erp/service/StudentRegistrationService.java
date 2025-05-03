package com.z7i.erp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.z7i.erp.dto.HallTicketResponse;
import com.z7i.erp.dto.PaymentRequest;
import com.z7i.erp.dto.StudentRegistrationRequest;
import com.z7i.erp.model.StudentRegistration;
import com.z7i.erp.repository.StudentRegistrationRepository;

@Service
public class StudentRegistrationService implements StudentRegistrationServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(StudentRegistrationService.class);

    private final StudentRegistrationRepository studentRegistrationRepository;

    public StudentRegistrationService(StudentRegistrationRepository studentRegistrationRepository) {
        this.studentRegistrationRepository = studentRegistrationRepository;
    }

    @Autowired
    private StudentRegistrationRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public StudentRegistration registerStudent(StudentRegistrationRequest request) {
        if (repository.findByRegistrationNumber(request.getRegistrationNumber()).isPresent()) {
            throw new IllegalArgumentException("Student with registration number " + request.getRegistrationNumber() + " already exists.");
        }

        StudentRegistration student = new StudentRegistration();
        student.setRegistrationNumber(request.getRegistrationNumber());
        student.setMobileNumber(request.getMobileNumber());
        student.setFirstName(request.getFirstName());
        student.setMiddleName(request.getMiddleName());
        student.setLastName(request.getLastName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setStream(request.getStream());
        student.setCategory(request.getCategory());
        student.setTestDateDay(request.getTestDateDay());
        student.setTestDateMonth(request.getTestDateMonth());
        student.setTestDateYear(request.getTestDateYear());
        student.setRegistrationCentreCode(request.getRegistrationCentreCode());
        student.setStudyCentreCode(request.getStudyCentreCode());
        student.setTestCentreCode(request.getTestCentreCode());
        student.setClassPresentlyStudying(request.getClassPresentlyStudying());
        student.setEmail(request.getEmail() != null ? objectMapper.convertValue(request.getEmail(), java.util.Map.class) : null);
        student.setMobile(request.getMobile() != null ? objectMapper.convertValue(request.getMobile(), java.util.Map.class) : null);
        student.setPreferredEmail(request.getPreferredEmail());
        student.setPreferredMobile(request.getPreferredMobile());
        student.setAddress(request.getAddress());
        student.setSchool(request.getSchool());
        student.setParents(request.getParents());
        student.setAchievements(request.getAchievements());
        student.setSiblings(request.getSiblings());
        student.setCreatedAt(java.time.LocalDateTime.now());

        return repository.save(student);
    }



    @Override
    public boolean processPayment(PaymentRequest paymentRequest) {
        try {
            // Stub for payment processing logic
            // In real implementation, integrate with payment gateway here
            // For now, just return true to indicate success
            return true;
        } catch (Exception e) {
            logger.error("Error processing payment: ", e);
            return false;
        }
    }

    @Override
    public Optional<HallTicketResponse> generateHallTicket(String registrationNumber) {
        try {
            Optional<StudentRegistration> studentOpt = studentRegistrationRepository.findByRegistrationNumber(registrationNumber);
            if (studentOpt.isPresent()) {
                StudentRegistration student = studentOpt.get();
                HallTicketResponse response = new HallTicketResponse();
                response.setRegistrationNumber(student.getRegistrationNumber());
                // response.setName(student.getStudentFirstName() + " " + (student.getStudentMiddleName() != null ? student.getStudentMiddleName() + " " : "") + student.getStudentLastName());
                // response.setAddress(student.getCurrentAddress());
                // response.setEmailId(student.getStudentEmail());
                // response.setProgramOpted(student.getStreamChoice());
                // response.setTestCentre(student.getTestCentreCode());
                // response.setStudyCentreOpted(student.getStudyCentreCode());
                // response.setTestDate(student.getTestDate());
                response.setExamSchedule(""); // Not available in current model, can be added if needed
                response.setReportingTime(""); // Not available in current model, can be added if needed
                response.setRegistrationDate(student.getCreatedAt() != null ? student.getCreatedAt().toLocalDate() : null);
                response.setControllingFiitJeeCentre(""); // Not available in current model, can be added if needed
                response.setOmrSheetNumbers(""); // Not available in current model, can be added if needed
                return Optional.of(response);
            }
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error generating hall ticket: ", e);
            return Optional.empty();
        }
    }
}
