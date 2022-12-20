package com.crdz.credzy.service;

import com.crdz.credzy.model.CustomerVerification;
import com.crdz.credzy.repository.CustomerVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerVerificationService {

    @Autowired
    CustomerVerificationRepository cvRepo;

    public Boolean verifyOtp(long customerId, String otp) {
        CustomerVerification customerVerification = cvRepo.getReferenceByCustomerId(customerId);
        return customerVerification.getOtp().equals(otp) && customerVerification.getValidTill().isAfter(LocalDateTime.now());
    }

    public void updateCustomerVerification(long id, String otp) {
        CustomerVerification cv = cvRepo.getReferenceByCustomerId(id);
        cv.setOtp(otp);
        cv.setValidTill(LocalDateTime.now().plusMinutes(10));
        cvRepo.save(cv);
    }
}
