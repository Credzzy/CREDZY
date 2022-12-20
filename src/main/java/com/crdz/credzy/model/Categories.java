package com.crdz.credzy.model;


import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table
@Entity
@Data
public class Categories {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String catName;
    private LocalDate addedOn;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] catImg;
}
