package com.crdz.credzy.repository;

import com.crdz.credzy.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByMobileNoOrEmail(String phoneNumber, String email);

    Customer getReferenceByEmail(String email);
}
