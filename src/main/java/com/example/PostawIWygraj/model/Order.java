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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createDate = new Date();
    @Column(name = "ID_ASSORTMENT")
    private Long idAssortment;
    @Column(name = "ID_PROVIDER")
    private Long idProvider;
    @Column(name = "PRICE_PURCHASE")
    private BigDecimal pricePurchase;
    @Column(name = "QUANTITY_PURCHASE")
    private BigDecimal quantityPurchase;
    @Column(name = "VALUE_PURCHASE")
    private BigDecimal valuePurchase;
    @Column(name = "ID_CUSTOMER")
    private Long idCustomer;
    @Column(name = "PRICE_SELL")
    private BigDecimal priceSell;
    @Column(name = "QUANTITY_SELL")
    private BigDecimal quantitySell;
    @Column(name = "VALUE_SELL")
    private BigDecimal valueSell;
    private BigDecimal profit;
    @Column(name = "ID_USER")
    private Long idUser;

}
