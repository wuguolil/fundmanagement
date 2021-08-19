package com.example.fund;

import com.example.manager.Manager;
import com.example.position.Position;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "fundId")
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fundId;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "manager_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Manager manager;

    @OneToMany(mappedBy = "fund", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Position> positions;

    public Fund(String name, Manager manager, Set<Position> positions) {
        this.name = name;
        this.manager = manager;
        this.positions = positions;
    }

    public Fund(int fundId, String name, Manager manager, Set<Position> positions) {
        this.fundId = fundId;
        this.name = name;
        this.manager = manager;
        this.positions = positions;
    }

    public Fund(int fundId, String name, Manager manager) {
        this.fundId = fundId;
        this.name = name;
        this.manager = manager;
    }

    public Fund() {
    }

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }
}
