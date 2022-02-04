package com.example.walletmng.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "wallet")
public class Wallet {

    @Id
    @Column(name = "mobileno",unique = true)
    private String mobileno;

    @Column(name = "balance")
    private double balance;

    @OneToMany(mappedBy = "wallet",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //@JsonIgnore
    private List<Transaction>transaction;

    @Override
    public String toString() {
        return "Wallet{" +
                "mobileno='" + mobileno + '\'' +
                ", balance=" + balance +
                ", transaction=" + transaction +
                '}';
    }
}
