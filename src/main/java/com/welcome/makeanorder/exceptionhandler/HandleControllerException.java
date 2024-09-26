package com.welcome.makeanorder.exceptionhandler;

import com.welcome.makeanorder.controller.customexception.NoCustomerFoundException;
import com.welcome.makeanorder.controller.customexception.NoSellerFoundException;
import com.welcome.makeanorder.enums.MessageStatusType;
import com.welcome.makeanorder.helper.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleControllerException {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> badCredentialsException(){
        return new ResponseEntity<>("Credentials are not valid!!!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> usernameNotFoundException(){
        return new ResponseEntity<>("Username is not valid!!!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSellerFoundException.class)
    public ResponseEntity<Map<String, String>> noSellerFound(NoSellerFoundException ex){
        return APIResponse.prepareHash(HttpStatus.NOT_FOUND, MessageStatusType.FAIL, "Seller not found");
    }

    @ExceptionHandler(NoCustomerFoundException.class)
    public ResponseEntity<Map<String, String>> noCustomerFound(NoCustomerFoundException ex){
        return APIResponse.prepareHash(HttpStatus.NOT_FOUND, MessageStatusType.FAIL, "Customer not found");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, List<String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return APIResponse.prepareErrorHash(HttpStatus.BAD_REQUEST,errors);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleSQLExceptions(
            RuntimeException ex) {
        String error_meesage = ex.getLocalizedMessage();
        return APIResponse.prepareHash(HttpStatus.CONFLICT, MessageStatusType.FAIL, error_meesage);
    }
}
