package com.jurepinjuh.demo.Controllers.HttpModels;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty(message = "validation.login.username.notEmpty")
    private String username;
    @NotEmpty(message = "validation.login.password.notEmpty")
    private String password;

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
