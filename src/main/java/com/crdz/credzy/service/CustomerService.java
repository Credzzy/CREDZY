package com.crdz.credzy.service;

import com.crdz.credzy.dtos.UserSignupDto;
import com.crdz.credzy.model.Customer;
import com.crdz.credzy.model.CustomerVerification;
import com.crdz.credzy.repository.CustomerRepository;
import com.crdz.credzy.repository.CustomerVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerVerificationRepository cvRepo;

    public Customer createCustomer(UserSignupDto input, String otp) {
        Customer customer = saveCustomer(input);
        saveCustomerVerification(customer.getId(),otp);
        return customer;
    }

    private void saveCustomerVerification(long customerId, String otp) {

        CustomerVerification cv = new CustomerVerification();
        cv.setCustomerId(customerId);
        cv.setValidTill(LocalDateTime.now().plusMinutes(10));
        cv.setOtp(otp);
        cvRepo.save(cv);
    }

    private Customer saveCustomer(UserSignupDto input) {
        Customer customer = new Customer();
        customer.setName(input.getName());
        customer.setMobileNo(input.getMobileNo());
        customer.setSubscriptionValidityTill(LocalDate.now().plusDays(7));
        customer.setEmail(input.getEmail());
        customer.setCredzyPoints(100);
        customer.setCity(input.getCity());
        return customerRepository.save(customer);
    }


    public boolean checkExistingUser(UserSignupDto input) {
        String phoneNumber = input.getMobileNo();
        String email = input.getEmail();
        return customerRepository.existsByMobileNoOrEmail(phoneNumber, email);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.getReferenceByEmail(email);
    }
}
