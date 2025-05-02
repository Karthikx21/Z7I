package com.z7i.erp.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public StudentRegistration registerStudent(StudentRegistrationRequest request) {
        try {
            // Check for duplicate registration number
            if (studentRegistrationRepository.findByRegistrationNumber(request.getRegistrationNumber()).isPresent()) {
                throw new IllegalArgumentException("Student with registration number " + request.getRegistrationNumber() + " already exists.");
            }

            StudentRegistration student = new StudentRegistration();

            student.setRegistrationNumber(request.getRegistrationNumber());
            student.setStudentMobileNumber(request.getStudentMobileNumber());
            student.setStudentFirstName(request.getStudentFirstName());
            student.setStudentMiddleName(request.getStudentMiddleName());
            student.setStudentLastName(request.getStudentLastName());
            student.setDateOfBirth(request.getDateOfBirth());
            student.setGender(request.getGender());
            student.setStreamChoice(request.getStreamChoice());
            student.setCategory(request.getCategory());
            student.setTestDate(request.getTestDate());
            student.setRegistrationCentreCode(request.getRegistrationCentreCode());
            student.setStudyCentreCode(request.getStudyCentreCode());
            student.setTestCentreCode(request.getTestCentreCode());
            student.setCurrentClass(request.getCurrentClass());
            student.setHallTicketMode(request.getHallTicketMode());
            student.setStudentEmail(request.getStudentEmail());
            student.setFatherEmail(request.getFatherEmail());
            student.setMotherEmail(request.getMotherEmail());
            student.setFatherMobile(request.getFatherMobile());
            student.setMotherMobile(request.getMotherMobile());
            student.setPreferredEmail(request.getPreferredEmail());
            student.setPreferredMobile(request.getPreferredMobile());
            student.setCurrentAddress(request.getCurrentAddress());
            student.setCurrentPin(request.getCurrentPin());
            student.setPermanentAddress(request.getPermanentAddress());
            student.setPermanentPin(request.getPermanentPin());
            student.setSchoolName(request.getSchoolName());
            student.setSchoolAddress(request.getSchoolAddress());
            student.setSchoolCity(request.getSchoolCity());
            student.setSchoolState(request.getSchoolState());
            student.setSchoolPin(request.getSchoolPin());
            student.setSchoolTelNo(request.getSchoolTelNo());
            student.setSchoolBoard(request.getSchoolBoard());
            student.setCurrentOrLastClassRank(request.getCurrentOrLastClassRank());
            student.setPrincipalName(request.getPrincipalName());
            student.setTeacherNameMathematics(request.getTeacherNameMathematics());
            student.setTeacherNamePhysics(request.getTeacherNamePhysics());
            student.setTeacherNameChemistry(request.getTeacherNameChemistry());
            student.setTeacherNameBiology(request.getTeacherNameBiology());
            student.setFatherName(request.getFatherName());
            student.setFatherQualification(request.getFatherQualification());
            student.setFatherOccupation(request.getFatherOccupation());
            student.setFatherDesignation(request.getFatherDesignation());
            student.setMotherName(request.getMotherName());
            student.setMotherQualification(request.getMotherQualification());
            student.setMotherOccupation(request.getMotherOccupation());
            student.setMotherDesignation(request.getMotherDesignation());
            student.setScholasticAchievements(request.getScholasticAchievements());

            student.setCreatedAt(LocalDateTime.now());
            student.setUpdatedAt(LocalDateTime.now());

            return studentRegistrationRepository.save(student);
        } catch (IllegalArgumentException e) {
            logger.error("Error registering student: ", e);
            throw e;
        }
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
                response.setName(student.getStudentFirstName() + " " + (student.getStudentMiddleName() != null ? student.getStudentMiddleName() + " " : "") + student.getStudentLastName());
                response.setAddress(student.getCurrentAddress());
                response.setEmailId(student.getStudentEmail());
                response.setProgramOpted(student.getStreamChoice());
                response.setTestCentre(student.getTestCentreCode());
                response.setStudyCentreOpted(student.getStudyCentreCode());
                response.setTestDate(student.getTestDate());
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
