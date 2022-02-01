package com.example.walletmng.Response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseResponse {

    public static ResponseEntity<Object>generateResponse(String message, HttpStatus status,Object respobj)
    {
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("message",message);
        map.put("status",status);
        map.put("data",respobj);
        return new ResponseEntity<Object>(map,status);
    }
}
