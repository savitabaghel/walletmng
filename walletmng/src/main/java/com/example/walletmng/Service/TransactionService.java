package com.example.walletmng.Service;


import com.example.walletmng.Repository.TransactionRepository;
import com.example.walletmng.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction findby(long id)
    {   Transaction result=null;
        if(transactionRepository.existsById(id))
        { result= transactionRepository.findById(id).get();
        return result;}
        return result;
    }
    public List<Transaction>findTransaction()

    {
        return transactionRepository.findAll();
    }
    public List<Transaction>getAllTransactionOfUser(String mobileno)
    {
        List<Transaction>list1=transactionRepository.findBypayeemobile(mobileno);
        List<Transaction>list2=transactionRepository.findBypayermobile(mobileno);
        List<Transaction> result = new ArrayList<>(list1.size() + list2.size());
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }
}
