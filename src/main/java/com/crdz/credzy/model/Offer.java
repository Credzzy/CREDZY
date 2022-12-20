package com.crdz.credzy.model;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table
@Entity
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private LocalDate addedOn;
    private String cityId;
    private long merchantId;
    private String offerName;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] offerImg;
    private long offerAmount;
    private long totalAmount;
    private long redeemAmount;
    private LocalDate validTill;
    private String termsAndConditions;
}