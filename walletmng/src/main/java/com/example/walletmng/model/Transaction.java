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
import java.util.Date;

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

    @Column(name = "TransactionDate")
    private Date date;

    @NotNull
    @Column(name = "payerMobile")
    private String  payermobile;

    @NotNull
    @Column(name = "payeemobile")
    private String payeemobile;

    @Column(name = "Amount")
    private Double amount;



}
