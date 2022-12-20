package com.crdz.credzy.dtos;

import lombok.Data;

@Data
public class TopBrandOutputDto {

    private long merchantId;
    private String firmName;
    private byte[] logo;
    private String benefitUpTo;
}
