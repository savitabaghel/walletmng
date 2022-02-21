package com.example.walletmng.Service;


import com.example.walletmng.Controller.WalletController;
import com.example.walletmng.dao.Holder;
import com.example.walletmng.Repository.TransactionRepository;
import com.example.walletmng.Repository.WalletRepository;
import com.example.walletmng.model.Transaction;
import com.example.walletmng.model.Wallet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Component
@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger logger= LogManager.getLogger(WalletService.class);

    public List<Wallet>findAllWallet()
    {

        logger.info("Service:All Wallet : getAllWallet request");
        logger.warn("Service : Fetching list of Wallets  : ");

        return walletRepository.findAll();
    }
    public Wallet findOne(String mobileno)
    {
        logger.info(" Service:One Wallet: getWallet by mobile number  request");

        Wallet exists=walletRepository.findByMobileno(mobileno);
        return exists;
    }
    public Wallet create(Wallet wallet)
    {

        logger.info("Service:Create Wallet  : create wallet for a user ");
        Wallet exists= null;
                exists=walletRepository.findByMobileno(wallet.getMobileno());
         if(exists!=null);
        {
            logger.warn("wallet already present");
        }
        return walletRepository.save(wallet);



    }
    public Wallet addMoney(double money,String mobileno)

    {
        logger.info("Service:Add money   : Add money in wallet");
        Wallet exists=walletRepository.findByMobileno(mobileno);
       if(exists!=null)
       {

           logger.info("Wallet for these user is exist");
        double currentBalance=exists.getBalance();
        double finalBalance=currentBalance+money;
        exists.setBalance(finalBalance);
        walletRepository.save(exists);

        Transaction crt=new Transaction();
        crt.setPayermobile(mobileno);
        crt.setPayeemobile(null);
        crt.setDate(new Date());
        crt.setAmount(money);
        transactionRepository.save(crt);
        return exists;
       }
       else{
           logger.error("Failed : due to wallet not exists");
           return null;}

    }

    public boolean transaction(Holder holder)
    {
        logger.info("Service:make transaction service  : Add money in wallet");
        Wallet payewallet=walletRepository.findByMobileno(holder.getPayembileno());

        Wallet payerwallet=walletRepository.findByMobileno(holder.getPayermobileno());

        if(payewallet==null||payerwallet==null)
        {
            logger.error("Wallet not exist!!!");
            return false;
        }
        double payeeamount=payewallet.getBalance();
        double payerAmount=payerwallet.getBalance();
        double amount=holder.getAmount();

        System.out.println(amount);

         if(payerAmount-amount>=0)
        {

            payerAmount = payerAmount-amount;
            payeeamount=payeeamount+amount;
            payewallet.setBalance(payeeamount);
            payerwallet.setBalance(payerAmount);

            Transaction crt=new Transaction();

            crt.setAmount(amount);

            crt.setDate(new Date());
            crt.setPayermobile(holder.getPayermobileno());

            crt.setPayeemobile(holder.getPayembileno());


            transactionRepository.save(crt);
            logger.info("Amount Transfer done!!");

            return true;

        }
        else{
             logger.warn("Insufficient Amount!!");
            return false;
           }

    }





}
