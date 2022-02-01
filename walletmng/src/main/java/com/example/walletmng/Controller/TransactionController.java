package com.example.walletmng.Controller;

import com.example.walletmng.Response.BaseResponse;
import com.example.walletmng.Service.TransactionService;
import com.example.walletmng.model.Transaction;
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

    @GetMapping("/transaction/{id}")
    ResponseEntity<Object> findone(@PathVariable long id)
    {
        Transaction result=transactionService.findby(id);
        if(result!=null)
            return BaseResponse.generateResponse("Retrieve successfully", HttpStatus.OK,result);
        else
            return BaseResponse.generateResponse("Cannot retrieve ",HttpStatus.BAD_REQUEST,null);
    }
    @GetMapping("/transaction")
    ResponseEntity<Object> findAllTransaction()
    {
        try {
            List<Transaction> result=transactionService.findTransaction();
            return BaseResponse.generateResponse("Successfully fatched",HttpStatus.OK,result);
        }
        catch (Exception e)
        {
            return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }
}
