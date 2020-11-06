package com.jurepinjuh.demo.Services;

import java.security.NoSuchAlgorithmException;

public interface IPasswordService {
    String hashPassword(String password) throws NoSuchAlgorithmException;
}
