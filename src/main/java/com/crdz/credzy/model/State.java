package com.crdz.credzy.model;

import lombok.Data;

import jakarta.persistence.*;

@Table
@Entity
@Data
public class State {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String State;
}
