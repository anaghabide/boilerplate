package com.beta.replyservice.controller;

import com.beta.replyservice.ReplyMessage;
import com.beta.replyservice.handler.ReplyHandler;
import com.beta.replyservice.handler.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyController {

    @Autowired
    ReplyHandler replyHandler;

    private Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @GetMapping("/reply")
    public ReplyMessage replying() {

        return new ReplyMessage("Message is empty");
    }

    @GetMapping("/reply/{message}")
    public ReplyMessage replying(@PathVariable String message) {
        ReplyMessage replyMessage;
        if(Validator.validateRequest(message)){
            logger.info("Message is valid");
            replyMessage  = replyHandler.handleMeassage(message);
        }else{
            logger.error("Message is not valid");
            throw new IllegalArgumentException("Invalid message");
        }
        return replyMessage;
    }
}