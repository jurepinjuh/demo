package com.jurepinjuh.demo.Services;

import org.apache.logging.log4j.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordService implements IPasswordService {

    private  String salt="HHASDUASDaIDJA(#19838hasf8h3h19hASfhuaisfhaiusfh";

    @Override
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        return md.digest(password.getBytes(StandardCharsets.UTF_8)).toString();
    }
}
