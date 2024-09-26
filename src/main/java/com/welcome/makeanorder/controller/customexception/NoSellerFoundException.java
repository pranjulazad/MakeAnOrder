package com.welcome.makeanorder.controller.customexception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSellerFoundException extends Exception{
    String message;
}
