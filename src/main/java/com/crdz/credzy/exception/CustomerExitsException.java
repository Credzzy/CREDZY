package com.crdz.credzy.exception;


/*

    This call will be used in FUTURE ENHANCEMENTS.

*/

public class CustomerExitsException extends RuntimeException{

    public CustomerExitsException(String phoneNo) {
        System.out.println("User already exists with phone number: " + phoneNo);
    }

}
