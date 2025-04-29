 package com.z7i.erp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_registration")
public class StudentRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", length = 50)
    private String registrationNumber;

    @Column(name = "student_mobile_number", length = 15, nullable = false)
    private String studentMobileNumber;

    @Column(name = "student_first_name", length = 100, nullable = false)
    private String studentFirstName;

    @Column(name = "student_middle_name", length = 100)
    private String studentMiddleName;

    @Column(name = "student_last_name", length = 15, nullable = false)
    private String studentLastName;

    @Column(name = "date_of_birth", nullable = false)
    private java.time.LocalDate dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "stream_choice", length = 50)
    private String streamChoice;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "test_date")
    private java.time.LocalDate testDate;

    @Column(name = "registration_centre_code", length = 20)
    private String registrationCentreCode;

    @Column(name = "study_centre_code", length = 20)
    private String studyCentreCode;

    @Column(name = "test_centre_code", length = 20)
    private String testCentreCode;

    @Column(name = "current_class", length = 20)
    private String currentClass;

    @Column(name = "hall_ticket_mode", length = 20)
    private String hallTicketMode;

    @Column(name = "student_email", length = 255)
    private String studentEmail;

    @Column(name = "father_email", length = 255)
    private String fatherEmail;

    @Column(name = "mother_email", length = 255)
    private String motherEmail;

    @Column(name = "father_mobile", length = 15)
    private String fatherMobile;

    @Column(name = "mother_mobile", length = 15)
    private String motherMobile;

    @Column(name = "preferred_email", length = 20)
    private String preferredEmail;

    @Column(name = "preferred_mobile", length = 20)
    private String preferredMobile;

    @Column(name = "current_address", columnDefinition = "TEXT")
    private String currentAddress;

    @Column(name = "current_pin", length = 10)
    private String currentPin;

    @Column(name = "permanent_address", columnDefinition = "TEXT")
    private String permanentAddress;

    @Column(name = "permanent_pin", length = 10)
    private String permanentPin;

    @Column(name = "school_name", length = 255)
    private String schoolName;

    @Column(name = "school_address", columnDefinition = "TEXT")
    private String schoolAddress;

    @Column(name = "school_city", length = 100)
    private String schoolCity;

    @Column(name = "school_state", length = 100)
    private String schoolState;

    @Column(name = "school_pin", length = 10)
    private String schoolPin;

    @Column(name = "school_tel_no", length = 20)
    private String schoolTelNo;

    @Column(name = "school_board", length = 100)
    private String schoolBoard;

    @Column(name = "current_or_last_class_rank", length = 50)
    private String currentOrLastClassRank;

    @Column(name = "principal_name", length = 100)
    private String principalName;

    @Column(name = "teacher_name_mathematics", length = 100)
    private String teacherNameMathematics;

    @Column(name = "teacher_name_physics", length = 100)
    private String teacherNamePhysics;

    @Column(name = "teacher_name_chemistry", length = 100)
    private String teacherNameChemistry;

    @Column(name = "teacher_name_biology", length = 100)
    private String teacherNameBiology;

    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "father_qualification", length = 100)
    private String fatherQualification;

    @Column(name = "father_occupation", length = 100)
    private String fatherOccupation;

    @Column(name = "father_designation", length = 100)
    private String fatherDesignation;

    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Column(name = "mother_qualification", length = 100)
    private String motherQualification;

    @Column(name = "mother_occupation", length = 100)
    private String motherOccupation;

    @Column(name = "mother_designation", length = 100)
    private String motherDesignation;

    @Column(name = "scholastic_achievements", length = 100)
    private String scholasticAchievements;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getStudentMobileNumber() {
        return studentMobileNumber;
    }

    public void setStudentMobileNumber(String studentMobileNumber) {
        this.studentMobileNumber = studentMobileNumber;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public java.time.LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.time.LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStreamChoice() {
        return streamChoice;
    }

    public void setStreamChoice(String streamChoice) {
        this.streamChoice = streamChoice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public java.time.LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(java.time.LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getRegistrationCentreCode() {
        return registrationCentreCode;
    }

    public void setRegistrationCentreCode(String registrationCentreCode) {
        this.registrationCentreCode = registrationCentreCode;
    }

    public String getStudyCentreCode() {
        return studyCentreCode;
    }

    public void setStudyCentreCode(String studyCentreCode) {
        this.studyCentreCode = studyCentreCode;
    }

    public String getTestCentreCode() {
        return testCentreCode;
    }

    public void setTestCentreCode(String testCentreCode) {
        this.testCentreCode = testCentreCode;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public String getHallTicketMode() {
        return hallTicketMode;
    }

    public void setHallTicketMode(String hallTicketMode) {
        this.hallTicketMode = hallTicketMode;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getFatherEmail() {
        return fatherEmail;
    }

    public void setFatherEmail(String fatherEmail) {
        this.fatherEmail = fatherEmail;
    }

    public String getMotherEmail() {
        return motherEmail;
    }

    public void setMotherEmail(String motherEmail) {
        this.motherEmail = motherEmail;
    }

    public String getFatherMobile() {
        return fatherMobile;
    }

    public void setFatherMobile(String fatherMobile) {
        this.fatherMobile = fatherMobile;
    }

    public String getMotherMobile() {
        return motherMobile;
    }

    public void setMotherMobile(String motherMobile) {
        this.motherMobile = motherMobile;
    }

    public String getPreferredEmail() {
        return preferredEmail;
    }

    public void setPreferredEmail(String preferredEmail) {
        this.preferredEmail = preferredEmail;
    }

    public String getPreferredMobile() {
        return preferredMobile;
    }

    public void setPreferredMobile(String preferredMobile) {
        this.preferredMobile = preferredMobile;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCurrentPin() {
        return currentPin;
    }

    public void setCurrentPin(String currentPin) {
        this.currentPin = currentPin;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentPin() {
        return permanentPin;
    }

    public void setPermanentPin(String permanentPin) {
        this.permanentPin = permanentPin;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolCity() {
        return schoolCity;
    }

    public void setSchoolCity(String schoolCity) {
        this.schoolCity = schoolCity;
    }

    public String getSchoolState() {
        return schoolState;
    }

    public void setSchoolState(String schoolState) {
        this.schoolState = schoolState;
    }

    public String getSchoolPin() {
        return schoolPin;
    }

    public void setSchoolPin(String schoolPin) {
        this.schoolPin = schoolPin;
    }

    public String getSchoolTelNo() {
        return schoolTelNo;
    }

    public void setSchoolTelNo(String schoolTelNo) {
        this.schoolTelNo = schoolTelNo;
    }

    public String getSchoolBoard() {
        return schoolBoard;
    }

    public void setSchoolBoard(String schoolBoard) {
        this.schoolBoard = schoolBoard;
    }

    public String getCurrentOrLastClassRank() {
        return currentOrLastClassRank;
    }

    public void setCurrentOrLastClassRank(String currentOrLastClassRank) {
        this.currentOrLastClassRank = currentOrLastClassRank;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getTeacherNameMathematics() {
        return teacherNameMathematics;
    }

    public void setTeacherNameMathematics(String teacherNameMathematics) {
        this.teacherNameMathematics = teacherNameMathematics;
    }

    public String getTeacherNamePhysics() {
        return teacherNamePhysics;
    }

    public void setTeacherNamePhysics(String teacherNamePhysics) {
        this.teacherNamePhysics = teacherNamePhysics;
    }

    public String getTeacherNameChemistry() {
        return teacherNameChemistry;
    }

    public void setTeacherNameChemistry(String teacherNameChemistry) {
        this.teacherNameChemistry = teacherNameChemistry;
    }

    public String getTeacherNameBiology() {
        return teacherNameBiology;
    }

    public void setTeacherNameBiology(String teacherNameBiology) {
        this.teacherNameBiology = teacherNameBiology;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherQualification() {
        return fatherQualification;
    }

    public void setFatherQualification(String fatherQualification) {
        this.fatherQualification = fatherQualification;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getFatherDesignation() {
        return fatherDesignation;
    }

    public void setFatherDesignation(String fatherDesignation) {
        this.fatherDesignation = fatherDesignation;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherQualification() {
        return motherQualification;
    }

    public void setMotherQualification(String motherQualification) {
        this.motherQualification = motherQualification;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getMotherDesignation() {
        return motherDesignation;
    }

    public void setMotherDesignation(String motherDesignation) {
        this.motherDesignation = motherDesignation;
    }

    public String getScholasticAchievements() {
        return scholasticAchievements;
    }

    public void setScholasticAchievements(String scholasticAchievements) {
        this.scholasticAchievements = scholasticAchievements;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
