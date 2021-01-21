package com.jurepinjuh.demo.Controllers.HttpModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError {


    public Date getDateOfError() {
        return dateOfError;
    }

    public void setDateOfError(Date dateOfError) {
        this.dateOfError = dateOfError;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public ValidationError(Date dateOfError, List<String> messages) {
        this.dateOfError = dateOfError;
        this.messages = messages;
    }

    private Date dateOfError;
    private List<String> messages;


}
