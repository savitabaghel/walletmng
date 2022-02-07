package com.example.walletmng;


import com.example.walletmng.Controller.WalletController;
import com.example.walletmng.Service.WalletService;
import com.example.walletmng.model.Wallet;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class WalletControllerTest {

    @Mock
    private WalletService walletService;

    private Wallet wallet;
    private List<Wallet>walletList;

    @InjectMocks
    private WalletController walletController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setWallet()
    {
        wallet=new Wallet("9109549374",300.0,null);
        mockMvc= MockMvcBuilders.standaloneSetup(walletController).build();
    }
    @AfterEach
    public void tearWallet()
    {
        wallet=null;
    }

    @Test
    public void createWalletTest()throws Exception
    {
        when(walletService.create(any())).thenReturn(wallet);
        mockMvc.perform(post("/w/wallet").contentType(MediaType.APPLICATION_JSON).content(asJsonString(wallet))).andExpect(status().isCreated());
        verify(walletService,times(1)).create(any());

    }
    @Test
    public void getAllWalletTest()throws Exception
    {
        when(walletService.findAllWallet()).thenReturn(walletList);
        mockMvc.perform(get("/w/wallet").contentType(MediaType.APPLICATION_JSON).content(asJsonString(wallet))).andDo(MockMvcResultHandlers.print());
        verify(walletService).findAllWallet();
        verify(walletService,times(1)).findAllWallet();
    }
    @Test
    public void getWalletByMobileNoTest() throws Exception
    {
        when(walletService.findOne(wallet.getMobileno())).thenReturn(wallet);
        mockMvc.perform(get("/w/wallet/9109549374").contentType(MediaType.APPLICATION_JSON).content(asJsonString(wallet))).andExpect(status().isOk());
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
