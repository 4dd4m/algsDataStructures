package localhost.linkedlist;

import java.util.Scanner;
import java.io.*;
import java.util.*;

public class BalancedExpression{

    private String expr;
    private String user;
    Scanner scan = new Scanner(System.in);
    final String[] openingBraces = {"(","[","{"};
    final String[] closingBraces = {")","]","}"};

    public BalancedExpression(){
        //String user = scan.nextLine();
        user = "(3+4[4*7]-5)(3+2)";
        toString(user);
    }

    public void parseExpression(){
        Stack<String> exprStack =  new Stack<String>();
        String valid = "valid";
        String parseString = user;
        for(int i=0;i < parseString.length();i++){
            String ch =  String.valueOf(parseString.charAt(i)); 
            
            if(isOpen(ch) == true){
                System.out.println("---->   " + ch);
                exprStack.push(ch);
                exprStack.toString();
            }
            if(isClose(ch) == true){
                try {
                    String pop = exprStack.pop();
                    System.out.println("<---   " + invertBrace(ch));
                    if(!pop.equals(invertBrace(ch))){
                        System.out.println("No match");
                        valid = "not valid";
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Not Valid Expression");
                    valid = "not valid";
                    break;
                } 
            }
        }
        if(exprStack.isEmpty() == false){
        valid = "not valid";
        }
        System.out.println("The expression is " + valid);
    }

    public String invertBrace(String brace){
        String invert = null;
        switch (brace) {
            case ")" -> invert = "(";
            case "]" -> invert = "[";
            case "}" -> invert = "{";
        }
        return invert;
    }

    public boolean isOpen(String x){
        for(int i=0;i < openingBraces.length ;i++){
            if(x.equals(openingBraces[i])){
                return true;
            }
        }
        return false;
    }

    public boolean isClose(String x){
        for(int i=0;i < openingBraces.length ;i++){
            if(x.equals(closingBraces[i])){
                return true;
            }
        }
        return false;
    }

    public void toString(String xyz){
        System.out.println(xyz);
    }

    public static void main (String args[]){
        String expr = "";
        BalancedExpression balance = new BalancedExpression();
        balance.parseExpression();
    }
}
