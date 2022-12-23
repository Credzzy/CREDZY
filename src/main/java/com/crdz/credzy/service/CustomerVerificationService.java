package com.crdz.credzy.service;

import com.crdz.credzy.dtos.CustomerOutputDto;
import com.crdz.credzy.model.Customer;
import com.crdz.credzy.model.CustomerVerification;
import com.crdz.credzy.repository.CustomerRepository;
import com.crdz.credzy.repository.CustomerVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerVerificationService {

    @Autowired
    CustomerVerificationRepository cvRepo;

    @Autowired
    CustomerRepository customerRepository;

    public CustomerOutputDto verifyOtp(long customerId, String otp) {
        CustomerVerification customerVerification = cvRepo.getReferenceByCustomerId(customerId);
        if(customerVerification.getOtp().equals(otp) && customerVerification.getValidTill().isAfter(LocalDateTime.now())) {
            Customer customer = customerRepository.getReferenceById(customerId);
            CustomerOutputDto customerOutputDto = new CustomerOutputDto();
            customerOutputDto.setName(customer.getName());
            customerOutputDto.setCredzyPoints(customer.getCredzyPoints());;
            customerOutputDto.setValidityTill(customer.getSubscriptionValidityTill());
            customerOutputDto.setOTPVerified("OTP verified");
            return customerOutputDto;
        }
        else {
            CustomerOutputDto customerOutputDto = new CustomerOutputDto();
            customerOutputDto.setOTPVerified("OTP verification failed");
            return customerOutputDto;
        }
    }

    public void updateCustomerVerification(long id, String otp) {
        CustomerVerification cv = cvRepo.getReferenceByCustomerId(id);
        cv.setOtp(otp);
        cv.setValidTill(LocalDateTime.now().plusMinutes(10));
        cvRepo.save(cv);
    }
}
