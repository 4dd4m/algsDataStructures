/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.linkedlist;

/**
 *
 * @author adam
 */
public class java {
    
    private static int calls = 0;
    static int[] data = new int[21];
    
    
    
    
    public static long fib(int n){
       data[n]+=1;
       if (n == 0) return 0;
       if (n == 1) return 1;
 
        return fib(n-1)+fib(n-2);
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < data.length; i++) {
            data[i] = 0;
        }
        System.out.println(fib(20));
        for (int i = 0; i < data.length; i++) {
            System.out.println("fibonacci(" + i + ") is called by " + data[i] + " times");
        }
        
    }
    
}
