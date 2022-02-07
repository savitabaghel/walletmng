package com.example.walletmng;


import com.example.walletmng.Controller.TransactionController;
import com.example.walletmng.Service.TransactionService;
import com.example.walletmng.model.Transaction;
import com.example.walletmng.model.Wallet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc

public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    private Transaction transaction;

    private List<Transaction>transactionList;

    @InjectMocks
    private TransactionController transactionController;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setTransaction()
    {
        transaction=new Transaction(1,new Date(),"9109549374","9988223344",30.0,null);
        mockMvc= MockMvcBuilders.standaloneSetup(transactionController).build();
    }
    @AfterEach
    public void tearWallet()
    {
        transaction=null;
    }
    @Test
    public void getTransactionById()throws Exception
    {
        when(transactionService.findby(transaction.getId())).thenReturn(transaction);
        mockMvc.perform(MockMvcRequestBuilders.get("/t/transaction/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transaction))).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTransactionTest()throws Exception
    {
        when(transactionService.findTransaction()).thenReturn(transactionList);
        mockMvc.perform(MockMvcRequestBuilders.get("/t/transaction").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transaction))).andDo(MockMvcResultHandlers.print());
        verify(transactionService).findTransaction();
        verify(transactionService,times(1)).findTransaction();
    }

    public static String asJsonString(final Object obj)
    {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
