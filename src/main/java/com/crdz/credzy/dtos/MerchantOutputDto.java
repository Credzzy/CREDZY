package com.crdz.credzy.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MerchantOutputDto {

    private String logo;
    private String firmName;
    private String facebook;
    private String instagram;
    private String mobile;
    private String whatsapp;
    private String address;
    private String gmap;

    private List<OfferDto> offerList;
}
