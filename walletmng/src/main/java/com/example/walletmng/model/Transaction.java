package com.example.walletmng.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transaction")
public class Transaction {

    @Id
    @Column(name = "Id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private Timestamp transactionTime;

    @NotNull
    @Column(name = "payerMobile")
    private String  payermobile;

    @NotNull
    @Column(name = "payeemobile")
    private String payeemobile;

    @Column(name = "Amount")
    private Double amount;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mobileno")
    private Wallet wallet;
}