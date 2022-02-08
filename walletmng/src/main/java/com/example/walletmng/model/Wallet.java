package com.example.walletmng.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

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
    @Column(name = "walletId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long walletId;

    @Column(name = "mobileno",unique = true)
    private String mobileno;


    @Column(name = "balance")
    private double balance;





}
