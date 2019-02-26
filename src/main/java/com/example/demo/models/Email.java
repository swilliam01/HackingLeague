package com.example.demo.models;

public class Email {
    private String emailTo;
    private String emailFrom;
    private String emailBody;

    public Email() {
    }

    public Email(String emailTo, String emailFrom, String emailBody) {
        this.emailTo = emailTo;
        this.emailFrom = emailFrom;
        this.emailBody = emailBody;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }
}
