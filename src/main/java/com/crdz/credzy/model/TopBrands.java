package com.crdz.credzy.model;

import lombok.Data;

import jakarta.persistence.*;

@Table
@Entity
@Data
public class TopBrands {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long merchantId;
    private String BenefitUpTo;
    private boolean active;
    private String link;

}
