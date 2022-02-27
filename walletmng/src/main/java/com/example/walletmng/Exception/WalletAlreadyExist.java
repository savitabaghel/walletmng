package com.example.walletmng.Exception;

public class WalletAlreadyExist extends RuntimeException
{

    public WalletAlreadyExist(String mobileNumber)
    {
        super("Wallet Already registered with mobile NUmber : " + mobileNumber);
    }

}
