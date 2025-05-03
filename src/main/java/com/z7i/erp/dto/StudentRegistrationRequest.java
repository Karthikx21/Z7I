package com.z7i.erp.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// StudentRegistrationRequest.java
import lombok.Data;

@Data
public class StudentRegistrationRequest {
    private String registrationNumber;
    private String mobileNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private List<String> stream;
    private List<String> category;
    private String testDateDay;
    private String testDateMonth;
    private String testDateYear;
    private String registrationCentreCode;
    private String studyCentreCode;
    private String testCentreCode;
    private String classPresentlyStudying;
    private Map<String, String> email;
    private Map<String, String> mobile;
    private String preferredEmail;
    private String preferredMobile;
    private Map<String, Object> address;
    private Map<String, Object> school;
    private Map<String, Object> parents;
    private Map<String, Object> achievements;
    private List<Map<String, Object>> siblings;

    

    /**
     * @return String return the registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * @param registrationNumber the registrationNumber to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * @return String return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return LocalDate return the dateOfBirth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return List<String> return the stream
     */
    public List<String> getStream() {
        return stream;
    }

    /**
     * @param stream the stream to set
     */
    public void setStream(List<String> stream) {
        this.stream = stream;
    }

    /**
     * @return List<String> return the category
     */
    public List<String> getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(List<String> category) {
        this.category = category;
    }

    /**
     * @return String return the testDateDay
     */
    public String getTestDateDay() {
        return testDateDay;
    }

    /**
     * @param testDateDay the testDateDay to set
     */
    public void setTestDateDay(String testDateDay) {
        this.testDateDay = testDateDay;
    }

    /**
     * @return String return the testDateMonth
     */
    public String getTestDateMonth() {
        return testDateMonth;
    }

    /**
     * @param testDateMonth the testDateMonth to set
     */
    public void setTestDateMonth(String testDateMonth) {
        this.testDateMonth = testDateMonth;
    }

    /**
     * @return String return the testDateYear
     */
    public String getTestDateYear() {
        return testDateYear;
    }

    /**
     * @param testDateYear the testDateYear to set
     */
    public void setTestDateYear(String testDateYear) {
        this.testDateYear = testDateYear;
    }

    /**
     * @return String return the registrationCentreCode
     */
    public String getRegistrationCentreCode() {
        return registrationCentreCode;
    }

    /**
     * @param registrationCentreCode the registrationCentreCode to set
     */
    public void setRegistrationCentreCode(String registrationCentreCode) {
        this.registrationCentreCode = registrationCentreCode;
    }

    /**
     * @return String return the studyCentreCode
     */
    public String getStudyCentreCode() {
        return studyCentreCode;
    }

    /**
     * @param studyCentreCode the studyCentreCode to set
     */
    public void setStudyCentreCode(String studyCentreCode) {
        this.studyCentreCode = studyCentreCode;
    }

    /**
     * @return String return the testCentreCode
     */
    public String getTestCentreCode() {
        return testCentreCode;
    }

    /**
     * @param testCentreCode the testCentreCode to set
     */
    public void setTestCentreCode(String testCentreCode) {
        this.testCentreCode = testCentreCode;
    }

    /**
     * @return String return the classPresentlyStudying
     */
    public String getClassPresentlyStudying() {
        return classPresentlyStudying;
    }

    /**
     * @param classPresentlyStudying the classPresentlyStudying to set
     */
    public void setClassPresentlyStudying(String classPresentlyStudying) {
        this.classPresentlyStudying = classPresentlyStudying;
    }

    /**
     * @return Map<String, String> return the email
     */
    public Map<String, String> getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(Map<String, String> email) {
        this.email = email;
    }

    /**
     * @return Map<String, String> return the mobile
     */
    public Map<String, String> getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(Map<String, String> mobile) {
        this.mobile = mobile;
    }

    /**
     * @return String return the preferredEmail
     */
    public String getPreferredEmail() {
        return preferredEmail;
    }

    /**
     * @param preferredEmail the preferredEmail to set
     */
    public void setPreferredEmail(String preferredEmail) {
        this.preferredEmail = preferredEmail;
    }

    /**
     * @return String return the preferredMobile
     */
    public String getPreferredMobile() {
        return preferredMobile;
    }

    /**
     * @param preferredMobile the preferredMobile to set
     */
    public void setPreferredMobile(String preferredMobile) {
        this.preferredMobile = preferredMobile;
    }

    /**
     * @return Map<String, Object> return the address
     */
    public Map<String, Object> getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Map<String, Object> address) {
        this.address = address;
    }

    /**
     * @return Map<String, Object> return the school
     */
    public Map<String, Object> getSchool() {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(Map<String, Object> school) {
        this.school = school;
    }

    /**
     * @return Map<String, Object> return the parents
     */
    public Map<String, Object> getParents() {
        return parents;
    }

    /**
     * @param parents the parents to set
     */
    public void setParents(Map<String, Object> parents) {
        this.parents = parents;
    }

    /**
     * @return Map<String, Object> return the achievements
     */
    public Map<String, Object> getAchievements() {
        return achievements;
    }

    /**
     * @param achievements the achievements to set
     */
    public void setAchievements(Map<String, Object> achievements) {
        this.achievements = achievements;
    }

    /**
     * @return List<Map<String, Object>> return the siblings
     */
    public List<Map<String, Object>> getSiblings() {
        return siblings;
    }

    /**
     * @param siblings the siblings to set
     */
    public void setSiblings(List<Map<String, Object>> siblings) {
        this.siblings = siblings;
    }

}

