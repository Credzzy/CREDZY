package com.crdz.credzy.dtos;

import java.time.LocalDateTime;

public interface CustomerOrderDto {

    long getOrderId();
    String getOfferName();
    String getUniqueCode();
    LocalDateTime getValidTill();
    String getOrderState();
    String getMerchantName();
    String getOrderTime();
    String getCustomerName();
}
