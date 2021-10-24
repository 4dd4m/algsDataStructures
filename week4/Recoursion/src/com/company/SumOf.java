package com.company;

public class SumOf {

    public static int sumOf(int i){
        if(i == 1) return 1;
        else return i + sumOf((i -1));
    }








    public static void main(String[] args) {
        System.out.println("The sum of up to 10: " + sumOf(10));

    }
}
