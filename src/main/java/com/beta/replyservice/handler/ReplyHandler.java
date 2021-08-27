package com.beta.replyservice.handler;

import com.beta.replyservice.ReplyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReplyHandler {
    @Autowired
    RuleHandler ruleHandler;


    public ReplyHandler() {
    }


    public ReplyMessage handleMeassage(String message) {
        List<Object> splitMessage = Arrays.asList(message.split("-"));
        String rule = (String) splitMessage.get(0);
        List<String> ruleList = Arrays.asList(rule.split(""));
        String inputMessage = (String) splitMessage.get(1);
        for(String i: ruleList){
            inputMessage = ruleHandler.applyRule(Integer.parseInt(i), inputMessage);
        }
        ReplyMessage replyMessage = new ReplyMessage(inputMessage);
        return replyMessage;
    }

}
