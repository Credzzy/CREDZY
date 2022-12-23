package com.crdz.credzy.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerOutputDto {

    private String name;
    private Long credzyPoints;
    private LocalDate validityTill;
    private String OTPVerified;

}
