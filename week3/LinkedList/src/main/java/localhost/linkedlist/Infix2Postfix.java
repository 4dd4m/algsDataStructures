package localhost.linkedlist;

    import java.util.Scanner;
    import localhost.linkedlist.*;

    public class Infix2Postfix {

        public String expr;
        public Stack<String> stack = new Stack<String>();
    public String output = "";
    

    public Infix2Postfix(){
         //expr = "(3+4)*2";
        //expr = "4-(1+2)*3"; //ok
        //expr = "4-1+2*3"; //precedence
        System.out.print(">>> ");
        Scanner scan = new Scanner(System.in);
        expr = scan.nextLine();
        convert();
        calculate();
        scan.close();
        }

    public String convert(){

        for(int i=0;i < expr.length() ;i++){
            String ch = String.valueOf(expr.charAt(i));
             switch(ch){
                case "0": case "1": case "2": case "3":
                case "4": case "5": case "6": case "7":
                case "8": case "9":{
                   output += ch;
                   break;
                }
             }
            switch(ch){//paren push to stack
                case "(":{
                   stack.push(ch);
                   break;
                }
                case ")":{ //item is )        
                String popped;
                boolean run = true;
                while (run) {
                      popped = stack.pop();        
                      if(!popped.equals("(")){             
                              output += popped;
                      }else if(popped.equals("(")){
                          run = false;
                      }              
               
              if(stack.isEmpty() == true){ 
                      run = false;
                  }            
                  } //while
                break;
                } //case
            } //switch
            
            
            switch(ch){ //operand pushed onto the stack
                case "+": case "-": case "*":{
   
                if(stack.isEmpty() == false ){
                    String peek = stack.peek();
                    
                    if(peek.equals("(")){
                        stack.push(ch);
                        break;
                    }
                
                if(precedence(ch,peek) == 1){
                 
                    stack.push(ch);
                }else{
                   
                    output += stack.pop();
                     stack.push(ch);
                }
                
                }else{
                    stack.push(ch);
                 }
                
            }//case
            }//switch
                
            }
        
             //System.out.println("Out: " + output);  
        
        boolean run = true;
        while(run){
            if(stack.isEmpty() == false){
                output+= stack.pop();
            }
            if(stack.isEmpty() == true) run = false;
        }

        return "";
    }

    public int precedence(String op1, String op2){
        //  1 -> have priority
        //  0 -> equal
        // -1 -> error
        if(op1.equals("*") && op2.equals("+")){
            return 1;
        }
        else if(op1.equals("*") && op2.equals("-")){
            return 1;
        }
        else if(op1.equals("*") && op2.equals("*")){
            return 0;
        }
        else if(op1.equals("+") && op2.equals("+")){
            return 0;
        }
        else if(op1.equals("+") && op2.equals("-")){
            return 0;
        }
        else if(op1.equals("+") && op2.equals("*")){
            return 0;
        }
         else if(op1.equals("-") && op2.equals("+")){
            return 0;
        }
        else if(op1.equals("-") && op2.equals("-")){
            return 0;
        }
         else if(op1.equals("-") && op2.equals("*")){
            return 0;
        }
        return -1;
    }



    public String showExpr(){
        return expr;
    }

    public boolean isOperator(String x){
        switch(x){
            case "+": case "-": case "*":
                return true;
        }
        return false;

    }
    
    public String calculate(){
        stack.clear();
        
        
        for (int i = 0; i < output.length(); i++) {
            String ch = String.valueOf(output.charAt(i));
            
            if(!isOperator(ch)){
                stack.push(ch);
            }else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int total = 0;
            
                switch(ch){
                    case "+":
                        total = num1+num2;
                        break;
                    case "-":
                        total = num2-num1;
                        break;
                    case "*":
                        total = num1 * num2;
                        break;
                }
                
                stack.push(String.valueOf(total));
            }
            //stack.toString();
        }
        
        return stack.pop();
    }


    public static void main(String[] args) {

        Infix2Postfix inf = new Infix2Postfix();
        //System.out.println(inf.showExpr());
        //Test of precendence
        //System.out.println("Operators: ** " +inf.precedence("*", "*")); 
        //System.out.println("Operators: *+ " +inf.precedence("*", "+")); 
//        System.out.println("Operators: *- " +inf.precedence("*", "-")); 
//        System.out.println("Operators: +* " +inf.precedence("+", "*")); 
//        System.out.println("Operators: ++ " +inf.precedence("+", "+")); 
//        System.out.println("Operators: +- " +inf.precedence("+", "-")); 
//        System.out.println("Operators: -* " +inf.precedence("-", "*")); 
//        System.out.println("Operators: -+ " +inf.precedence("-", "+")); 
//        System.out.println("Operators: -- " +inf.precedence("-", "-")); 

        //test of operators
//        System.out.println("* :" + inf.isOperator("*"));
//        System.out.println("+ :" + inf.isOperator("+"));
//        System.out.println("- :" + inf.isOperator("-"));
//        System.out.println("a :" + inf.isOperator("a"));

        
        System.out.println("Expr: " + inf.expr);
        System.out.println("Output: " + inf.output);
        System.out.println("Calculate: " + inf.calculate());
        //System.out.println("Final Stack: " + inf.stack.toString());

    }

}
