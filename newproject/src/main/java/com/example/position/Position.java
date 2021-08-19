package com.example.position;

import com.example.fund.Fund;
import com.example.security.Security;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "positionId")
public class Position {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int positionId;

    @ManyToOne
    @JoinColumn(name = "securityId")
    @JsonIdentityReference(alwaysAsId = true)
    private Security security;

    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private LocalDate datePurchased;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fund_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Fund fund;

    public Position(int positionId, int quantity, LocalDate datePurchased) {
        this.positionId = positionId;
        this.quantity = quantity;
        this.datePurchased = datePurchased;
    }

    public Position(int positionId) {
        this.positionId = positionId;
    }

    public Position(int positionId, Security security, int quantity, LocalDate datePurchased, Fund fund) {
        this.positionId = positionId;
        this.security = security;
        this.quantity = quantity;
        this.datePurchased = datePurchased;
        this.fund = fund;
    }

    public Position(Security security, int quantity, LocalDate datePurchased, Fund fund) {
        this.security = security;
        this.quantity = quantity;
        this.datePurchased = datePurchased;
        this.fund = fund;
    }

    public Position() {
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

}