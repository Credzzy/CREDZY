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
    private String banLink;
    private String crazyDealImg;

//    for future enhancements
//    private String merchantId;
//    private String city;

}
