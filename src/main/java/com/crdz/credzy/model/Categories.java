package com.crdz.credzy.model;


import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Table
@Entity
@Data
public class Categories {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String catName;
    private Date addedOn;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] catImg;
}
