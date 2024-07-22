package com.example.storeGenie.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {

    //this is created so that we cannot create object of this
    private CafeUtils() {

    }

    //making it static so that it can be accessed using classname.methodname
    public static ResponseEntity<String> getResponeEntity(String responseMessage , HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }
}
