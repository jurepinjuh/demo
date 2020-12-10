package com.jurepinjuh.demo.Controllers.HttpModels;

import javax.validation.constraints.NotEmpty;

public class RegisterRequest {
    @NotEmpty(message = "Email is required!")
    private String email;
    @NotEmpty(message = "Username is required!")
    private String username;
    @NotEmpty(message = "Password is required!")
    private String password;

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
