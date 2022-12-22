package com.crdz.credzy.dtos;

import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OfferDto {
    private Long offerId;
    private String offerName;
    private List<String> termsAndConditions;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] offerImg;
    private LocalDate validTillDate;
}
