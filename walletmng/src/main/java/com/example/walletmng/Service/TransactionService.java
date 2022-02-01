package com.example.walletmng.Service;


import com.example.walletmng.Repository.TransactionRepository;
import com.example.walletmng.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction findby(long id)
    {
        Transaction result= transactionRepository.findById(id).get();
        return result;
    }
    public List<Transaction>findTransaction()

    {
        return transactionRepository.findAll();
    }
}
