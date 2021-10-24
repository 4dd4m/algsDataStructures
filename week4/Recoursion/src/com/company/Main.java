package com.company;

public class Main {

    public static String toBinary(int i){

            if (i == 1 ){
                return "1";
            }else{
                return toBinary(i / 2) + i % 2;
            }


    }

    public static void main(String[] args) {
	System.out.print(toBinary(10));
    }
}
