package com.crdz.credzy.dtos;

import lombok.Data;

@Data
public class LoginDto {

    private String message;
    private long customerId;
    private String merchantName;
}
