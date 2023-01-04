package com.crdz.credzy.dtos;

import lombok.Data;

import java.util.Random;


@Data
public class SimpleMailTemplate {

//    private String body = "Hi, \n\n Here is your OTP for login in " + otpGenerator() + " \n\n Thank you for choosing Credzy.";

    private String subject = "OTP for CREDZY";


    public String getBody(String otp) {
        return "Hi, \n\n Here is your OTP to login: " + otp + " \n\n Thank you for choosing Credzy.";
    }

    public String otpGenerator() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }


}
