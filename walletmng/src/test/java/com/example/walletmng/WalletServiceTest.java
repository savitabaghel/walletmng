package com.example.walletmng;

import com.example.walletmng.dao.WalletRepository;
import com.example.walletmng.Service.WalletService;
import com.example.walletmng.model.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @MockBean
    private WalletRepository walletRepository;

    @Test
    public void getWalletTest()
    {
        when(walletRepository.findAll()).thenReturn((List<Wallet>) Stream.of(new Wallet(1,
                "9876654",
                30.0)).collect(Collectors.toList()));

        assertEquals(1,walletService.findAllWallet().size());

    }
    @Test
    public void getWalletByMobileTest() throws Exception
    {
        Wallet wallet=new Wallet();

        wallet.setMobileno("76584309");
        wallet.setBalance(40.0);
        when(walletRepository.findByMobileno("76584309")).thenReturn(wallet);
        assertThat(walletService.findOne("76584309")).isEqualTo(wallet);
    }

    @Test
    public void createWalletTest()
    {
        Wallet wallet=new Wallet(2,"98765432",220.0);
        when(walletRepository.save(wallet)).thenReturn(wallet);
        assertEquals(wallet,walletService.create(wallet));
    }

}
