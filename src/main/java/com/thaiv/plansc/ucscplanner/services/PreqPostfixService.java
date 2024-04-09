package com.thaiv.plansc.ucscplanner.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreqPostfixService {

    private HashMap<String, Integer> precedenceMap;
    private PreqService preqService;

    @Autowired
    public PreqPostfixService(PreqService preqService){
        this.preqService = preqService; 
        precedenceMap = initPrecedenceMap();
    }

    public HashMap<String, Integer> initPrecedenceMap(){
        HashMap<String, Integer> p = new HashMap<>();
        p.put("AND", 5);
        p.put("COMMA", 4);
        p.put("COMMA-AND", 3);
        p.put("COMMA-OR", 2);
        p.put("SEMI-AND", 1);
        p.put("SEMI-OR", 0);
        return p;
    }

    public String convertToPostfix(String preqStr){
        preqStr = cleanString(preqStr);

        String[] infixArray = preqStr.split(" ");
        infixArray = Arrays.copyOfRange(infixArray, 1, infixArray.length);
        
        LinkedList<String> postfixQueue = new LinkedList<String>();
        Stack<String> operatorStack = new Stack<String>();

        for(String s : infixArray){
            if(preqService.isOperand(s)){
                postfixQueue.addFirst(s);
            }
            if(precedenceMap.keySet().contains(s)){
                while(!operatorStack.isEmpty()){
                    if(precedenceMap.get(operatorStack.peek()) >=
                    precedenceMap.get(s)){
                        postfixQueue.addFirst(getOperator(operatorStack.pop()));
                    } else {
                        break;
                    }
                }
                operatorStack.push(s);
            }
        }
        while(!operatorStack.isEmpty()){
            postfixQueue.addFirst(getOperator(operatorStack.pop()));
        }

        return postfixQueue.toString();
    }

    public String cleanString(String preqStr){
        String replaced = preqStr.replaceAll(", and", "COMMA-AND")
                    .replaceAll(", or", "COMMA-OR")
                    .replaceAll("; or", " SEMI-OR")
                    .replaceAll("; and", " SEMI-AND")
                    .replaceAll(";", " SEMI")
                    .replaceAll(",", " COMMA")
                    .replaceAll("or", "OR")
                    .replaceAll("and", "AND");

        replaced = replaced.replaceAll("\\.", "");
        replaced = replaced.replaceAll("\\s+(\\d+)", "$1");

        return replaced;
    }

    public String getOperator(String operator){
        if(operator.equals("OR") || operator.equals("SEMI-OR") || operator.equals("COMMA-OR")){
            return "OR";
        }
        if(operator.equals("AND") || operator.equals("SEMI-AND") || operator.equals("COMMA-AND")){
            return "AND";
        }
        System.out.println("Invalid operator: " + operator);
        System.exit(1);
        return null;
    }

}
