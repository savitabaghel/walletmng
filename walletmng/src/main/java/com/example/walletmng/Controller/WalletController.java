package com.example.walletmng.Controller;


import com.example.walletmng.Response.BaseResponse;
import com.example.walletmng.Service.WalletService;
import com.example.walletmng.dao.Holder;
import com.example.walletmng.dao.WalletDao;
import com.example.walletmng.kafka.Consumer;
import com.example.walletmng.kafka.Producer;
import com.example.walletmng.model.Wallet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Autowired
    Producer producer;

    private static final Logger logger= LogManager.getLogger(WalletController.class);

    @GetMapping("/post")
    public void sendMessage(@RequestParam("msg") String msg)
    {
        producer.publishToTopic(msg);
    }

    @PostMapping("/wallet")
    public ResponseEntity<Object>createWallet(@RequestBody Wallet wallet) throws Exception
    {
             logger.info("Controller : API for creating  wallet reached to Controller");
             logger.warn("Controller : creating wallet for   mobile number : " + wallet.getMobileno());
             Wallet result=walletService.create(wallet);
             if(result!=null)
             {
                 logger.info("Wallet created successfully");
                 return BaseResponse.generateResponse("Successfully created", HttpStatus.CREATED, result);
             }

            else
            {

                 logger.error("Wallet not created");
                return BaseResponse.generateResponse("Cant created",HttpStatus.MULTI_STATUS,null);
            }


    }
    @GetMapping("/wallet")
    public ResponseEntity<Object>getWallet()
    {

        logger.info("Controller : API for Fetching all wallet reached to Controller");

        try {
            List<Wallet>result=walletService.findAllWallet();
            logger.info("Successfully fetched!!");
            return BaseResponse.generateResponse("Success",HttpStatus.OK,result);
        }
        catch (Exception e)
        {
            logger.error("Cant load wallet details");
            return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/wallet/{mobileno}")
    public ResponseEntity<Object>getbymobile(@PathVariable("mobileno")String mobileno) throws Exception
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
   public ResponseEntity<Object>transactionbymobile(@RequestBody Holder holder) throws Exception {

           try {

               boolean result=walletService.transaction(holder);
               return BaseResponse.generateResponse("Transaction successfully",HttpStatus.OK,null);
               }
           catch (Exception e)
           {
               return BaseResponse.generateResponse("Unsuccessful",HttpStatus.MULTI_STATUS,null);
           }

   }
   @PutMapping("/wallet/{mobileno}")
    public ResponseEntity<Object>addInWallet(@RequestBody WalletDao walletDao, @PathVariable("mobileno")String mobileno) throws Exception {


           try {
               Wallet wallet = walletService.addMoney(walletDao.getMoney(), mobileno);
               return BaseResponse.generateResponse("successfully added", HttpStatus.OK, wallet);
              }

       catch (Exception e)
       {
           return BaseResponse.generateResponse("Unsuccessfull",HttpStatus.BAD_REQUEST,null);
       }
   }

   @GetMapping("/")
    public String home()
   {
       return "welcome";
   }

}
