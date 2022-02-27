package com.example.walletmng.Controller;

import com.example.walletmng.Response.BaseResponse;
import com.example.walletmng.Service.TransactionService;
import com.example.walletmng.model.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/t")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    private static final Logger logger= LogManager.getLogger(TransactionController.class);

    @GetMapping("/transaction/{id}")
    ResponseEntity<Object> findone(@PathVariable long id) throws Exception
    {
        logger.info("Controller : API for fetching transaction detail reached to Controller");
        logger.warn("Controller : fetching detail for transaction id : " + id+"-->");

        try
        {
            Transaction result = transactionService.findby(id);
            return BaseResponse.generateResponse("Retrieve successfully", HttpStatus.OK, result);
        }
        catch (Exception e)
        {
            return BaseResponse.generateResponse("Cannot retrieve ", HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/transaction")
    ResponseEntity<Object> findAllTransaction() throws Exception
    {
        logger.info("Controller : API for fetching all transaction reached to Controller");


        try {
            List<Transaction> result = transactionService.findTransaction();

            return BaseResponse.generateResponse("Successfully fatched", HttpStatus.OK, result);
        } catch (Exception e) {
            return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/transaction/user")
    ResponseEntity<Object>findAllByUser(@PathVariable String mobileno)
    {
        try {
            logger.info("getting transaction history->");
            List<Transaction>result=transactionService.getTransactionForUser(mobileno);
            return BaseResponse.generateResponse("Retrieved",HttpStatus.OK,result);
        }
        catch (Exception e)
        {
            return BaseResponse.generateResponse("not retrieved",HttpStatus.MULTI_STATUS,null);
        }
    }
}
