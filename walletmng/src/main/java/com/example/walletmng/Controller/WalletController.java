package com.example.walletmng.Controller;


import com.example.walletmng.Response.BaseResponse;
import com.example.walletmng.Service.WalletService;
import com.example.walletmng.dao.Holder;
import com.example.walletmng.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/w")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/wallet")
    public ResponseEntity<Object>createWallet(@RequestBody Wallet wallet)
    {
         //System.out.println(wallet1);
            Wallet result=walletService.create(wallet);
            if(result!=null)
                return BaseResponse.generateResponse("Successfully created", HttpStatus.CREATED,result);
            else
                return BaseResponse.generateResponse("Cant created",HttpStatus.MULTI_STATUS,null);


    }
    @GetMapping("/wallet")
    public ResponseEntity<Object>getWallet()
    {
        try {
            List<Wallet>result=walletService.findAllWallet();
            return BaseResponse.generateResponse("Success",HttpStatus.OK,result);
        }
        catch (Exception e)
        {
            return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/wallet/{mobileno}")
    public ResponseEntity<Object>getbymobile(@PathVariable("mobileno")String mobileno)
    {
        try
        {
            Wallet wallet1=walletService.findOne(mobileno);
            return BaseResponse.generateResponse("Successful",HttpStatus.OK,wallet1);

        }
        catch (Exception e)
        {
            return BaseResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,null);
        }
    }
   @PostMapping("/transaction")
   public ResponseEntity<Object>transactionbymobile(@RequestBody Holder holder)
   {
           //System.out.println(holder);
           boolean result=walletService.transaction(holder);
           if(result)
               return BaseResponse.generateResponse("Transaction successfully",HttpStatus.OK,null);
           else
               return BaseResponse.generateResponse("Unsuccessful",HttpStatus.MULTI_STATUS,null);

   }
   @PutMapping("/wallet/{mobileno}")
    public ResponseEntity<Object>addInWallet(@RequestBody double money,@PathVariable("mobileno")String mobileno)
   {

       Wallet wallet=walletService.addMoney(money, mobileno);
       if(wallet!=null){
           return BaseResponse.generateResponse("successfully added",HttpStatus.OK,wallet);
       }
       else
       {
           return BaseResponse.generateResponse("wallet not present",HttpStatus.BAD_REQUEST,null);
       }
   }

   @GetMapping("/")
    public String home()
   {
       return "welcome";
   }

}
