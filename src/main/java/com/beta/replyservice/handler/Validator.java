package com.beta.replyservice.handler;

import java.util.Arrays;
import java.util.List;


public class Validator {

    public static boolean validateRequest(String message){
        boolean isValid = true;
        if(!message.contains("-")){
            isValid = false;
        }else {
            List<Object> splitMessage = Arrays.asList(message.split("-"));
            if(splitMessage.size() != 2 ){
                isValid = false;
            }
        }
        return isValid;
    }
}
