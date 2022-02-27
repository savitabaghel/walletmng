package com.example.walletmng.Exception;

public class InsufficientAmount extends RuntimeException {
    public InsufficientAmount(String mobileno)
    {
        super("Insufficient Amount in wallet with mobile no. "+mobileno);
    }
}
