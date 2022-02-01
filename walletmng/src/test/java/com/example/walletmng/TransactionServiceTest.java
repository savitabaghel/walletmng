package com.example.walletmng;

import com.example.walletmng.Repository.TransactionRepository;
import com.example.walletmng.Service.TransactionService;
import com.example.walletmng.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void getByIdtest()
    {
        Transaction transaction=new Transaction();
        transaction.setId(2065);
        transaction.setPayeemobile("987654");
        transaction.setPayermobile("456789");
        transaction.setTransactionTime(new Timestamp(new Date().getTime()));
        transaction.setAmount(40.0);
        transaction.setWallet(null);

        when(transactionRepository.findById(2065L)).thenReturn(Optional.of(transaction));
        assertThat(transactionService.findby(2065L)).isEqualTo(transaction);
    }
    @Test
    public void getAllTransactionTest()
    {
        Transaction transaction1=new Transaction();
        transaction1.setId(2066);
        transaction1.setPayeemobile("987654");
        transaction1.setPayermobile("456789");
        transaction1.setTransactionTime(new Timestamp(new Date().getTime()));
        transaction1.setAmount(40.0);
        transaction1.setWallet(null);

        Transaction transaction2=new Transaction();
        transaction2.setId(2067);
        transaction2.setPayeemobile("987654");
        transaction2.setPayermobile("456789");
        transaction2.setTransactionTime(new Timestamp(new Date().getTime()));
        transaction2.setAmount(40.0);
        transaction2.setWallet(null);

        List<Transaction>transactionList=new ArrayList<>();
        transactionList.add(transaction1);
        transactionList.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactionList);
        assertThat(transactionService.findTransaction()).isEqualTo(transactionList);


    }

}
