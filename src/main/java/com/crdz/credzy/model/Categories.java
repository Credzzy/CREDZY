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
    private long cityId;
    private String catImg;
}
