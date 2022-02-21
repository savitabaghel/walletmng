package com.example.walletmng.Service;


import com.example.walletmng.Repository.TransactionRepository;
import com.example.walletmng.kafka.Consumer;
import com.example.walletmng.model.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger= LogManager.getLogger(TransactionService.class);

    // to find transaction by a particular id
    public Transaction findby(long id)
    {
        logger.info("Service : Find transaction by id: Transaction Information for Id:");
        Transaction result= transactionRepository.findById(id).get();
        return result;
    }
    //to get list of all transaction
    public List<Transaction>findTransaction()

    {
        logger.info("Service : All transaction: Transaction List :");
        return transactionRepository.findAll();
    }

}
