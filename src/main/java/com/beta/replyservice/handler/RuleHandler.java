package com.beta.replyservice.handler;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class RuleHandler {
    private Map<Integer, Function<String, String>> ruleMap = new HashMap<>();

    public RuleHandler() {
        ruleMap.put(1, s ->  new StringBuilder(s).reverse().toString());
        ruleMap.put(2, s -> String.valueOf(DigestUtils.md5Digest(s.getBytes())));
    }


    public String applyRule(int rule, String message) {
        return ruleMap.get(rule).apply(message);
    }

    /**
     * method used to add new rule
     * @param ruleNumber
     * @param operation
     * @return
     * //TODO
     */
    public boolean addRule(int ruleNumber, String operation) {
        ruleMap.put(ruleNumber, message -> operation);
        return true;
    }

    /**
     * method used to delete rule
     * @param ruleNumner
     * @return
     * //TODO
     */
    public boolean deleteRule(int ruleNumner) {
        if(ruleMap.containsKey(ruleNumner)) {
            ruleMap.remove(ruleNumner);
        }
        return true;
    }
}
