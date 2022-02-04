package com.example.walletmng;

import com.example.walletmng.Repository.WalletRepository;
import com.example.walletmng.model.Wallet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class WalletRepositoryTest {

    @Autowired
    private WalletRepository walletRepository;
    private Wallet wallet;

    @BeforeEach
    public void setWallet()
    {
        wallet=new Wallet("9109549374",30.0,null);

    }

    @AfterEach
    public void tearDown()
    {
        walletRepository.deleteAll();
        wallet=null;
    }
    @Test
    public void getAllWalletTest()
    {
        Wallet wallet1=new Wallet("9109549374",300.0,null);
        Wallet wallet2=new Wallet("7865435678",8667.0,null);
        Wallet wallet3=new Wallet("9807654657",200.0,null);
        walletRepository.save(wallet1);
        walletRepository.save(wallet2);
        walletRepository.save(wallet3);

        List<Wallet>walletList=walletRepository.findAll();
        assertEquals("7865435678",walletList.get(0).getMobileno());


    }
   @Test
    public void getWalletByMobile()
   {
       walletRepository.save(wallet);
       Wallet wallet1=walletRepository.findByMobileno("9109549374");
       assertEquals("9109549374",wallet1.getMobileno());
   }

}
