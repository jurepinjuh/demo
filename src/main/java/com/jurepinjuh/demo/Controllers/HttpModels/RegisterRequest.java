package com.jurepinjuh.demo.Controllers.HttpModels;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterRequest {
    @NotEmpty(message = "validation.login.email.notEmpty")
    @Email(message = "validation.login.email.Email")
    private String email;
    @NotEmpty(message = "validation.login.username.notEmpty")
    private String username;
    @NotEmpty(message = "validation.login.password.notEmpty")
    private String password;

    public RegisterRequest(@NotEmpty(message = "validation.login.email.notEmpty") @Email(message = "validation.login.email.Email") String email, @NotEmpty(message = "validation.login.username.notEmpty") String username, @NotEmpty(message = "validation.login.password.notEmpty") String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public RegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
