package com.welcome.makeanorder.helper;

import com.welcome.makeanorder.enums.MessageStatusType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIResponse {

    public static ResponseEntity<Map<String, String>> prepareHash(HttpStatus code, MessageStatusType messageStatusType, String message) {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", messageStatusType.toString());
        map.put("message", message);
        return new ResponseEntity<>(map, code);
    }

    public static ResponseEntity<Map<String, List<String>>> prepareErrorHash(HttpStatus code, List<String> messages) {
        return new ResponseEntity<>(getErrorsMap(messages), code);
    }

    private static Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
