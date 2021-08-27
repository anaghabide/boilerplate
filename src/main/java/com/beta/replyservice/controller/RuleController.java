package com.beta.replyservice.controller;

import com.beta.replyservice.handler.RuleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RuleController {

    @Autowired
    private RuleHandler ruleService;

    @PostMapping("/addRule/{operation}")
    public void addRule(@PathVariable String operation) {
        List<String> splitRule = Arrays.asList(operation.split("-"));
        ruleService.addRule(Integer.parseInt(splitRule.get(0)), splitRule.get(1));
    }

    @DeleteMapping("/deleteRule/{number}")
    public void deleteRule(@PathVariable Integer number) {
        ruleService.deleteRule(number);
    }
}
