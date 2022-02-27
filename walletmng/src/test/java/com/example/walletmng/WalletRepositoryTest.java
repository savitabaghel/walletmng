package com.example.walletmng;

import com.example.walletmng.dao.WalletRepository;
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
        wallet=new Wallet(5,"9109549300",30.0);

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
        Wallet wallet1=new Wallet(8,"91099999",300.0);
        Wallet wallet2=new Wallet(9,"7865435678",8667.0);
        Wallet wallet3=new Wallet(19,"9807654657",200.0);
        walletRepository.save(wallet1);
        walletRepository.save(wallet2);
        walletRepository.save(wallet3);

        List<Wallet>walletList=walletRepository.findAll();
        assertEquals("7865435678",walletList.get(1).getMobileno());


    }
   @Test
    public void getWalletByMobile()
   {
       walletRepository.save(wallet);
       Wallet wallet1=walletRepository.findByMobileno("9109549300");
       assertEquals("9109549300",wallet1.getMobileno());
   }

}
