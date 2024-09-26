package com.welcome.makeanorder.controller.customexception;

import lombok.Getter;

@Getter
public class NoCustomerFoundException extends Exception {
    String message;
}
