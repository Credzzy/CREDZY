package com.crdz.credzy.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Data
public class Offers {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Date addedOn;
    private String city;
    private long merchantId;
    private String offerName;
    private String offerImg;
    private String minAmount;
    private String totalAmount;
    private Date validTill;
    private List<String> termsAndConditions;
}