package com.crdz.credzy.model;

import lombok.Data;

import jakarta.persistence.*;

@Table
@Entity
@Data
public class CrazyDeals {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long merchantId;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] crazyDealImg;

//    for future enhancements
//    private String merchantId;
//    private String city;

}
