package com.example.walletmng;

import com.example.walletmng.dao.TransactionRepository;
import com.example.walletmng.Service.TransactionService;
import com.example.walletmng.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        transaction.setId(2069);
        transaction.setPayeemobile("987654");
        transaction.setPayermobile("456789");
        transaction.setDate(new Date());
        transaction.setAmount(40.0);


        when(transactionRepository.findById(2069L)).thenReturn(Optional.of(transaction));
        System.out.println(transactionService.findby(2069L));
        assertThat(transactionService.findby(2069L)).isEqualTo(transaction);
    }
    @Test
    public void getAllTransactionTest()
    {
        Transaction transaction1=new Transaction();
        transaction1.setId(2066);
        transaction1.setPayeemobile("987654");
        transaction1.setPayermobile("456789");
        transaction1.setDate(new Date());
        transaction1.setAmount(40.0);


        Transaction transaction2=new Transaction();
        transaction2.setId(2067);
        transaction2.setPayeemobile("987654");
        transaction2.setPayermobile("456789");
        transaction2.setDate(new Date());
        transaction2.setAmount(40.0);


        List<Transaction>transactionList=new ArrayList<>();
        transactionList.add(transaction1);
        transactionList.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactionList);
        assertThat(transactionService.findTransaction()).isEqualTo(transactionList);


    }

}
