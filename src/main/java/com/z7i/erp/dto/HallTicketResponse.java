package com.z7i.erp.dto;

import java.time.LocalDate;

public class HallTicketResponse {

    private String registrationNumber;
    private String name;
    private String address;
    private String emailId;
    private String programOpted;
    private String testCentre;
    private String studyCentreOpted;
    private LocalDate testDate;
    private String examSchedule;
    private String reportingTime;
    private LocalDate registrationDate;
    private String controllingFiitJeeCentre;
    private String omrSheetNumbers;

    // Getters and setters omitted for brevity

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getProgramOpted() {
        return programOpted;
    }

    public void setProgramOpted(String programOpted) {
        this.programOpted = programOpted;
    }

    public String getTestCentre() {
        return testCentre;
    }

    public void setTestCentre(String testCentre) {
        this.testCentre = testCentre;
    }

    public String getStudyCentreOpted() {
        return studyCentreOpted;
    }

    public void setStudyCentreOpted(String studyCentreOpted) {
        this.studyCentreOpted = studyCentreOpted;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getExamSchedule() {
        return examSchedule;
    }

    public void setExamSchedule(String examSchedule) {
        this.examSchedule = examSchedule;
    }

    public String getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(String reportingTime) {
        this.reportingTime = reportingTime;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getControllingFiitJeeCentre() {
        return controllingFiitJeeCentre;
    }

    public void setControllingFiitJeeCentre(String controllingFiitJeeCentre) {
        this.controllingFiitJeeCentre = controllingFiitJeeCentre;
    }

    public String getOmrSheetNumbers() {
        return omrSheetNumbers;
    }

    public void setOmrSheetNumbers(String omrSheetNumbers) {
        this.omrSheetNumbers = omrSheetNumbers;
    }
}
