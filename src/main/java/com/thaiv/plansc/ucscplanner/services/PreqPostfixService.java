package com.thaiv.plansc.ucscplanner.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PreqPostfixService {

    private HashMap<String, Integer> precedenceMap;

    public PreqPostfixService(){
        precedenceMap = initPrecedenceMap();
    }

    public HashMap<String, Integer> initPrecedenceMap(){
        HashMap<String, Integer> p = new HashMap<>();
        p.put("AND", 5);
        p.put("COMMA", 4);
        p.put("SEMI", 5);
        p.put("OR", 4);
        p.put("COMMA-AND", 3);
        p.put("COMMA-OR", 2);
        p.put("SEMI-AND", 1);
        p.put("SEMI-OR", 0);
        return p;
    }

    public String convertToPostfix(String preqStr){
        preqStr = cleanString(preqStr);
        System.out.println(preqStr);

        String[] infixArray = preqStr.split(" ");
        infixArray = Arrays.copyOfRange(infixArray, 1, infixArray.length);
        
        LinkedList<String> postfixQueue = new LinkedList<String>();
        Stack<String> operatorStack = new Stack<String>();

        for(String s : infixArray){
            if(isOperand(s)){
                postfixQueue.addLast(s);
            }
            if(precedenceMap.keySet().contains(s)){
                while(!operatorStack.isEmpty()){
                    if(precedenceMap.get(operatorStack.peek()) >=
                    precedenceMap.get(s)){
                        postfixQueue.addLast(getOperator(operatorStack.pop()));
                    } else {
                        break;
                    }
                }
                operatorStack.push(s);
            }
        }
        while(!operatorStack.isEmpty()){
            postfixQueue.addLast(getOperator(operatorStack.pop()));
        }

        return StringUtils.join(postfixQueue.toArray(), " ");
    }

    public String cleanString(String preqStr){
        System.out.println("preqpostfixservice: original preqstr: " + preqStr);
        String replaced = preqStr.replaceAll(", and", " COMMA-AND")
                    .replaceAll(", or", " COMMA-OR")
                    .replaceAll("; or", " SEMI-OR")
                    .replaceAll("; and", " SEMI-AND")
                    .replaceAll(";", " SEMI")
                    .replaceAll(",", " COMMA")
                    .replaceAll(" or ", " OR ")
                    .replaceAll(" and ", " AND ");

        int indexPreq = replaced.indexOf("Prerequisite(s):");
        if(indexPreq != -1){
            replaced = replaced.substring(indexPreq);
        }

        int indexPeriod = replaced.indexOf(".");
        if(indexPeriod != -1){
            replaced = replaced.substring(0, indexPeriod);
        }
        replaced = replaced.replaceAll("\\.", "");
        replaced = replaced.replaceAll("  ", " ");
        replaced = replaced.replaceAll("score of (\\d+) OR higher on the mathematics placement examination \\(MPE\\)", "MPE$1");
        replaced = replaced.replaceAll("mathematics placement \\(MP\\) score of (\\d+) OR higher", "MPE_$1");
        System.out.println(replaced);
        replaced = replaced.replaceAll("\\s+(\\d+)", "$1");

        return replaced;
    }

    public String getOperator(String operator){
        if(operator.equals("OR") || operator.equals("SEMI-OR") || operator.equals("COMMA-OR")){
            return "OR";
        }
        if(operator.equals("AND") || operator.equals("SEMI-AND") || operator.equals("COMMA-AND")
        ||operator.equals("SEMI") || operator.equals("COMMA")){
            return "AND";
        }
        System.out.println("Invalid operator: " + operator);
        System.exit(1);
        return null;
    }

    public boolean isOperand(String s){
        if(s.matches("[A-Za-z]+\\d+[A-Za-z]*")){
            return true;
        } else if(s.matches("[A-Za-z]+\\_\\d+[A-Za-z]*")){
            return true;
        } else if(s.equals("permission")){
            return true;
        } else if(s.equals("equivalent")){
            return true;
        }
        return false;
    }

}
