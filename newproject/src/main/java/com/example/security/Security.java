package com.example.security;

import com.example.position.Position;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "securityId")
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int securityId;

    @Column(nullable = false)
    private String symbol;

    @OneToMany(mappedBy = "security", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Position> positions;

    public Security() {
    }

    public Security(int securityId, String symbol) {
        this.securityId = securityId;
        this.symbol = symbol;
    }

    public Security(String symbol, Set<Position> positions) {
        this.symbol = symbol;
        this.positions = positions;
    }

    public Security(String symbol) {
        this.symbol = symbol;
    }

    public int getSecurityId() {
        return securityId;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }
}
