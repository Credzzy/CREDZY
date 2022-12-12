package com.crdz.credzy.model;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class OfferSubscriptionMapping {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long offerId;
    private long subscriptionId;
}
