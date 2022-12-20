package com.crdz.credzy.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Table(name = "Customer", schema = "credzy")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String mobileNo;
    private long credzyPoints;
    private long subscriptionId;
    private LocalDate subscriptionValidityTill;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
    private String city;
}
