package com.example.walletmng.dao;

import com.example.walletmng.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,String> {

    public Wallet findByMobileno(String mobileno);
}
