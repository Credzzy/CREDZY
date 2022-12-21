package com.crdz.credzy.dtos;

import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import lombok.Data;

import java.util.List;

@Data
public class OfferDto {
    private String offerName;
    private List<String> termsAndConditions;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] offerImg;
}
