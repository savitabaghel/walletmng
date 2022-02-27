package com.example.walletmng;


import com.example.walletmng.dao.TransactionRepository;
import com.example.walletmng.model.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;
    private Transaction transaction;

    @BeforeEach
    public void setUp()
    {
        transaction=new Transaction(208,null,"87657","56743",30.0);

    }
    @AfterEach
    public void tearDown()
    {
        transactionRepository.deleteAll();
        transaction=null;
    }
    @Test
    public void findAllTest()
    {

        transactionRepository.save(transaction);


        List<Transaction>transactionList=transactionRepository.findAll();


        assertEquals(30.0,transactionList.get(0).getAmount());
    }
    @Test
    public void deleteAllTest()
    {
       Transaction transaction1=new Transaction(30,null,"9109549374","9691984546",800.0);
       Transaction transaction2=new Transaction(20,null,"6754345676","876987554",200.0);
       transactionRepository.save(transaction1);
       transactionRepository.save(transaction2);

       transactionRepository.deleteAll();

       List<Transaction>transactionList=transactionRepository.findAll();

       assertEquals(0,transactionList.size());
    }

}
