package com.crdz.credzy.controller;


import com.crdz.credzy.dtos.*;
import com.crdz.credzy.model.Customer;
import com.crdz.credzy.repository.CustomerOrdersRepository;
import com.crdz.credzy.repository.CustomerRepository;
import com.crdz.credzy.service.CustomerService;
import com.crdz.credzy.service.CustomerVerificationService;
import com.crdz.credzy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Component
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "customer")
public class CustomerController {

    @Autowired
    EmailService emailService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerVerificationService cvService;

    @Autowired
    CustomerOrdersRepository customerOrdersRepository;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping
    @RequestMapping(path = "/signup/getOtp")
    public LoginDto customerSignup(@RequestBody UserSignupDto input){
        LoginDto loginDto = new LoginDto();
        SimpleMailTemplate simpleMailTemplate = new SimpleMailTemplate();
        String otp = simpleMailTemplate.otpGenerator();
        if(customerService.checkExistingUser(input)) {
            loginDto.setMessage("Customer already exists with this phone number or Email");
            return loginDto;
        }
        try {
            emailService.sendSimpleMessage(input.getEmail(), simpleMailTemplate.getSubject(), simpleMailTemplate.getBody(otp));
            Customer customer = customerService.createCustomer(input, otp);
            loginDto.setMessage("OTP sent successfully.");
            loginDto.setCustomerId(customer.getId());
            return loginDto;
        }
        catch (Exception e) {
            loginDto.setMessage("Failed to send OTP through E-mail, check entered E-mail address.");
            return loginDto;
        }
    }

    @PostMapping
    @RequestMapping(path = "/login/getOtp")
        public LoginDto customerLogin(@RequestBody UserSignupDto input){
        LoginDto loginDto = new LoginDto();
        SimpleMailTemplate simpleMailTemplate = new SimpleMailTemplate();
        String otp = simpleMailTemplate.otpGenerator();
        if(!customerService.checkExistingUser(input)) {
            loginDto.setMessage("User does not exists with this E-mail");      //later shift to mobile
            return loginDto;
        }
        Customer customer = customerService.getCustomerByEmail(input.getEmail());
        try {
            emailService.sendSimpleMessage(input.getEmail(), simpleMailTemplate.getSubject(), simpleMailTemplate.getBody(otp));
            cvService.updateCustomerVerification(customer.getId(), otp);
            loginDto.setMessage("OTP sent successfully to your Email");
            loginDto.setCustomerId(customer.getId());
            return loginDto;

        }
        catch (Exception e) {
            loginDto.setMessage("Failed to send OTP through E-mail, check entered E-mail address.");
            return loginDto;
        }
    }



    @PostMapping
    @RequestMapping(path = "/verifyOtp")
    public CustomerOutputDto verifyOtp(@RequestParam Long customerId, @RequestParam String otp) {
        return cvService.verifyOtp(customerId, otp);
    }

    @GetMapping
    @RequestMapping(path = "/getAll")
    public List<Customer> getAllCustomer() {
        return customerService.getAll();
    }


    @GetMapping
    @RequestMapping(path = "/get")
    public CustomerOutputDto getCustomer(@RequestParam Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        CustomerOutputDto customerOutputDto = new CustomerOutputDto();
        customerOutputDto.setName(customer.getName());
        customerOutputDto.setCredzyPoints(customer.getCredzyPoints());;
        customerOutputDto.setValidityTill(customer.getSubscriptionValidityTill());
        customerOutputDto.setOTPVerified(null);
        return customerOutputDto;
    }

    @GetMapping
    @RequestMapping(path = "/history")
    public List<CustomerOrderDto> getHistory(@RequestParam Long customerId) {
        return customerOrdersRepository.getAllByCustomerId(customerId);
    }
}
