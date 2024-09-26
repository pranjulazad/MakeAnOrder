package com.welcome.makeanorder.helper;

import com.welcome.makeanorder.enums.AddressType;
import com.welcome.makeanorder.enums.ProductType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ServiceHelper {

    public static boolean isValidProductType(String type){
        return Arrays.stream(ProductType.values()).map(val -> val.name().toLowerCase()).anyMatch(type.toLowerCase()::equals);
    }

    public static boolean isValidAddressType(String type){
        return Arrays.stream(AddressType.values()).map(val -> val.name().toLowerCase()).anyMatch(type.toLowerCase()::equals);
    }
}
