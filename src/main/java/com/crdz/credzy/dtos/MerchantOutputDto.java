package com.crdz.credzy.dtos;

import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import lombok.Data;

import java.util.List;

@Data
public class MerchantOutputDto {

    @Lob
    @Basic(fetch = FetchType.LAZY)
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
