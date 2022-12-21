package com.crdz.credzy.repository;

import com.crdz.credzy.model.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Long> {

    @Query(value = "SELECT offer_id FROM customer_orders WHERE customer_id = ?1", nativeQuery = true)
    List<Long> getReferenceByCustomerId(Long customerId);

    @Query(value = "SELECT * FROM customer_orders WHERE customer_id = ?1", nativeQuery = true)
    List<CustomerOrders> getAllByCustomerId(Long customerId);
}
