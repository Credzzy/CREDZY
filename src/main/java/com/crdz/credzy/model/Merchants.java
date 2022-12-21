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
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] logo;
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
}
