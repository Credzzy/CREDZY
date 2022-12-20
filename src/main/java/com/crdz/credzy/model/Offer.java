package com.crdz.credzy.model;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private LocalDate addedOn;
    private String city;
    private long merchantId;
    private String offerName;
    private String offerImg;
    private long offerAmount;
    private long totalAmount;
    private LocalDate validTill;
    private List<String> termsAndConditions;
}