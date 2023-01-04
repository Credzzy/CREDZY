package com.crdz.credzy.model;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table
@Entity
@Data
public class Merchants {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private LocalDate addedOn;
    private long cityId;
    private long stateId;
    private long categoryId;
    private String  logo;
    private String benefitUpTo;
    private boolean isTopBrand;
    private String firmName;
    private String contactPerson;
    private String facebook;
    private String instagram;
    private String mobile;
    private String whatsapp;
    private String address;
    private String gmap;
    private String password;
    private String merchantId;
    private String TopBrandImg;
}
