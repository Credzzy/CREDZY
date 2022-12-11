package com.crdz.credzy.repository;

import com.crdz.credzy.model.Customer;
import com.crdz.credzy.model.CustomerVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerVerificationRepository extends JpaRepository<CustomerVerification, Long> {
    CustomerVerification getReferenceByCustomerId(long customerId);
}
