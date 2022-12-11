package com.crdz.credzy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVerification {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long customerId;
    private String otp;
    private LocalDateTime validTill;
}
