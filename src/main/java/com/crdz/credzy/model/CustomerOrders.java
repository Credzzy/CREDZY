package com.crdz.credzy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table
@Entity
@Data
public class CustomerOrders {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long customerId;
    private long offerId;
    private LocalDateTime validTill;
    private String orderState;
    private String uniqueCode;
    private String merchantName;
    private long merchantId;
    private LocalDateTime orderTime;
}
