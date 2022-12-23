package com.crdz.credzy.repository;

import com.crdz.credzy.dtos.CustomerOrderDto;
import com.crdz.credzy.model.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Long> {

    @Query(value = "SELECT offer_id FROM customer_orders WHERE customer_id = ?1", nativeQuery = true)
    List<Long> getReferenceByCustomerId(Long customerId);

    @Query(value = "SELECT o.id as orderId, o.offer_name as OfferName," +
            " co.unique_code as uniqueCode, co.valid_till as validTill," +
            " co.order_state as orderState" +
            " FROM ebdb.customer_orders co join offer o" +
            " WHERE co.offer_id = o.id AND customer_id = ?1", nativeQuery = true)
    List<CustomerOrderDto> getAllByCustomerId(Long customerId);
}
