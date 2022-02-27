package com.example.walletmng.Exception;

public class WalletNotFoundException extends RuntimeException

{
    public WalletNotFoundException(String mobileNumber)
    {
        super("No Wallet Found With Mobile Number : " + mobileNumber);
    }
}
