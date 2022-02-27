package com.example.walletmng.Exception;

public class TransactionNotFoundException extends RuntimeException{
     public TransactionNotFoundException(long id)
    {
        super("Transaction with id "+id+" not found");
    }
}
