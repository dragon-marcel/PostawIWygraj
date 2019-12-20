package com.example.PostawIWygraj.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate ;
    @Column(name = "ID_ASSORTMENT")
    private Long idAssortment;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "ID")
    private Provider provider;
    @Column(name = "PRICE_PURCHASE")
    private BigDecimal pricePurchase;
    @Column(name = "QUANTITY_PURCHASE")
    private BigDecimal quantityPurchase;
    @Column(name = "VALUE_PURCHASE")
    private BigDecimal valuePurchase;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private Customer customer;
    @Column(name = "PRICE_SELL")
    private BigDecimal priceSell;
    @Column(name = "QUANTITY_SELL")
    private BigDecimal quantitySell;
    @Column(name = "VALUE_SELL")
    private BigDecimal valueSell;
    private BigDecimal profit;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
    

}
