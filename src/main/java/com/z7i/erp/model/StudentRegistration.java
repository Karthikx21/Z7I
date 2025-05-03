package com.z7i.erp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "registrations")
@Data
public class StudentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;
    private String mobileNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private java.util.List<String> stream;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private java.util.List<String> category;

    private String testDateDay;
    private String testDateMonth;
    private String testDateYear;
    private String registrationCentreCode;
    private String studyCentreCode;
    private String testCentreCode;
    private String classPresentlyStudying;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> email;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> mobile;

    private String preferredEmail;
    private String preferredMobile;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> address;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> school;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> parents;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> achievements;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Map<String, Object>> siblings;

    private LocalDateTime createdAt;

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public java.util.List<String> getStream() {
        return stream;
    }

    public void setStream(java.util.List<String> stream) {
        this.stream = stream;
    }

    public java.util.List<String> getCategory() {
        return category;
    }

    public void setCategory(java.util.List<String> category) {
        this.category = category;
    }

    public String getTestDateDay() {
        return testDateDay;
    }

    public void setTestDateDay(String testDateDay) {
        this.testDateDay = testDateDay;
    }

    public String getTestDateMonth() {
        return testDateMonth;
    }

    public void setTestDateMonth(String testDateMonth) {
        this.testDateMonth = testDateMonth;
    }

    public String getTestDateYear() {
        return testDateYear;
    }

    public void setTestDateYear(String testDateYear) {
        this.testDateYear = testDateYear;
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

    public String getClassPresentlyStudying() {
        return classPresentlyStudying;
    }

    public void setClassPresentlyStudying(String classPresentlyStudying) {
        this.classPresentlyStudying = classPresentlyStudying;
    }

    public Map<String, Object> getEmail() {
        return email;
    }

    public void setEmail(Map<String, Object> email) {
        this.email = email;
    }

    public Map<String, Object> getMobile() {
        return mobile;
    }

    public void setMobile(Map<String, Object> mobile) {
        this.mobile = mobile;
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

    public Map<String, Object> getAddress() {
        return address;
    }

    public void setAddress(Map<String, Object> address) {
        this.address = address;
    }

    public Map<String, Object> getSchool() {
        return school;
    }

    public void setSchool(Map<String, Object> school) {
        this.school = school;
    }

    public Map<String, Object> getParents() {
        return parents;
    }

    public void setParents(Map<String, Object> parents) {
        this.parents = parents;
    }

    public Map<String, Object> getAchievements() {
        return achievements;
    }

    public void setAchievements(Map<String, Object> achievements) {
        this.achievements = achievements;
    }

    public List<Map<String, Object>> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<Map<String, Object>> siblings) {
        this.siblings = siblings;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
