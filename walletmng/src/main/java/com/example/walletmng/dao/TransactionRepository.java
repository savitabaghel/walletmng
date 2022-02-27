package com.example.walletmng.dao;


import com.example.walletmng.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>
{

    Transaction findAllById(long id);
    @Query(value="SELECT * FROM transaction WHERE payee = ?1 OR payer = ?1 ORDER BY create_time DESC", nativeQuery = true)
    List<Transaction>findByMobile(String mobile);

}
