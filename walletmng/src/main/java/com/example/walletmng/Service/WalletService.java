package com.example.walletmng.Service;


import com.example.walletmng.Controller.Holder;
import com.example.walletmng.Repository.TransactionRepository;
import com.example.walletmng.Repository.WalletRepository;
import com.example.walletmng.model.Transaction;
import com.example.walletmng.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Wallet>findAllWallet()
    {
        return walletRepository.findAll();
    }
    public Wallet findOne(String mobileno)
    {
        Wallet exists=walletRepository.findByMobileno(mobileno);
        return exists;
    }
    public Wallet create(Wallet wallet)
    {
        return walletRepository.save(wallet);



    }
    public Wallet addMoney(double money,String mobileno)
    {
        Wallet exists=walletRepository.findByMobileno(mobileno);
       if(exists!=null)
       {
        double currentBalance=exists.getBalance();
        double finalBalance=currentBalance+money;
        exists.setBalance(finalBalance);
        walletRepository.save(exists);

        Transaction crt=new Transaction();
        crt.setPayermobile(mobileno);
        crt.setAmount(money);
        transactionRepository.save(crt);
        return exists;
       }
       else
           return null;

    }

    public boolean transaction(Holder holder)
    {
        System.out.println(holder.getPayembileno());
        Wallet payewallet=walletRepository.findByMobileno(holder.getPayembileno());
        System.out.println(payewallet);
        Wallet payerwallet=walletRepository.findByMobileno(holder.getPayermobileno());
        if(payewallet==null||payerwallet==null)
            return false;
        double payeeamount=payewallet.getBalance();
        double payerAmount=payerwallet.getBalance();
        double amount=holder.getAmount();

         if(payerAmount-amount>=0)
        {
            payerAmount = payerAmount-amount;
            payeeamount=payeeamount+amount;
            payewallet.setBalance(payeeamount);
            payerwallet.setBalance(payerAmount);

            Transaction crt=new Transaction();
            crt.setAmount(amount);
            crt.setPayermobile(holder.getPayermobileno());
            crt.setPayeemobile(holder.getPayembileno());

            transactionRepository.save(crt);

            return true;

        }
        else
            return false;

    }





}
