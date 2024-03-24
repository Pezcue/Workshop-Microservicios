package com.validador.validadorIntento.Models;

public class PersonaCsv {

    private String email;
    private String dateOfBirth;
    private String jobTitle;

    public PersonaCsv(String email, String dateOfBirth, String jobTitle) {
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getJobTitle() {
        return jobTitle;
    }

}
