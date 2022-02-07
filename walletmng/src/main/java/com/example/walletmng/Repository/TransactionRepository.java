package com.example.walletmng.Repository;


import com.example.walletmng.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>
{

    Transaction findAllById(long id);
    List<Transaction>findBypayeemobile(String payeemobile);
    List<Transaction>findBypayermobile(String payermobile);
}
