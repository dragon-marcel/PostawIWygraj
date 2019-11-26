package com.example.PostawIWygraj.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Long idCreateUser;
//    @JoinColumn(name = "user_id")
//    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private Date date;
    private Date time;
    private Long idProvider;
//    @JoinColumn(name = "provider_id")
//    @OneToOne(cascade = CascadeType.ALL)
    private Provider provider;
    private Long idAssortment;
//    @JoinColumn(name = "assortyment_id")
//    @OneToOne(cascade = CascadeType.ALL)
    private Assortment assortyment;
    private BigDecimal pricePurchase;
    private BigDecimal quantityPurchase;
    private Long idCustomer;
//    @JoinColumn(name = "customer_id")
//    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    private BigDecimal quantitySelling;
    private BigDecimal priceSelling;
    private BigDecimal profit;
    private String currency;
    
}
