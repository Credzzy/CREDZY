package com.crdz.credzy.repository;

import com.crdz.credzy.dtos.CustomerOrderDto;
import com.crdz.credzy.model.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Long> {

    @Query(value = "SELECT offer_id FROM customer_orders WHERE customer_id = ?1", nativeQuery = true)
    List<Long> getReferenceByCustomerId(Long customerId);

    @Query(value = "SELECT co.id as orderId, o.offer_name as OfferName," +
            " co.unique_code as uniqueCode, co.valid_till as validTill," +
            " co.order_state as orderState," +
            " co.merchant_name as merchantName," +
            " co.order_time as orderTime" +
            " FROM ebdb.customer_orders co join offer o" +
            " WHERE co.offer_id = o.id AND customer_id = ?1" +
            " ORDER BY order_time DESC", nativeQuery = true)
    List<CustomerOrderDto> getAllByCustomerId(Long customerId);

    @Query(value = "SELECT co.id as orderId, o.offer_name as OfferName," +
            " co.unique_code as uniqueCode, co.valid_till as validTill," +
            " co.order_state as orderState, co.order_time as orderTime," +
            " c.name as customerName" +
            " FROM customer_orders co  join offer o join customer c" +
            " WHERE co.offer_id = o.id  AND co.customer_id = c.id" +
            " AND co.merchant_id = ?1 AND co.order_state = 'Redeem'" +
            " ORDER BY co.order_time DESC", nativeQuery = true)
    List<CustomerOrderDto> getOrderByMerchant(Long merchantId);

    @Query(value = "SELECT co.id as orderId, o.offer_name as OfferName," +
            " co.unique_code as uniqueCode, co.valid_till as validTill," +
            " co.order_state as orderState, co.order_time as orderTime," +
            " c.name as customerName" +
            " FROM customer_orders co  join offer o join customer c" +
            " WHERE co.offer_id = o.id  AND co.customer_id = c.id" +
            " AND co.unique_code = ?1 AND co.merchant_id = ?2", nativeQuery = true)
    CustomerOrderDto findByUniqueCodeAndMerchantId(String uniqueCode, Long merchantId);

    @Query(value = "select count(*) from customer_orders" +
            " where customer_id = ?1" +
            " and merchant_id = ?2" +
            " and order_state = 'Availed'" +
            " and redeem_time >= date_sub(convert_tz(now(),'+00:00','+05:30'), INTERVAL 15 hour)", nativeQuery = true)
    int getCountOfOrderInLast15H(long customerId, long merchantId);
}
