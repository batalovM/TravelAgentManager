package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Сотрудники компании
public class CompanyRepresentative {
    private int companyRepresentativeId;
    private String lastName;
    private String firstName;
    private String surName;
    public CompanyRepresentative(){}

    public CompanyRepresentative(int companyRepresentativeId, String lastName, String firstName, String surName) {
        this.companyRepresentativeId = companyRepresentativeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
    }

    public int getCompanyRepresentativeId() {
        return companyRepresentativeId;
    }

    public void setCompanyRepresentativeId(int companyRepresentativeId) {
        this.companyRepresentativeId = companyRepresentativeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
