/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.linkedlist;

import java.util.Scanner;

public class TowersOfHanoi {
    
    private static final String SOURCE = "Source";
    private static final String SPARE = "Spare";
    private static final String TARGET = "Raget";
    
    public static void solveTowers(int numOfDiscs, String source, String spare, String target){
        if (numOfDiscs == 1) {
            System.out.println(source + " => " + target);
        }else{
            solveTowers( numOfDiscs - 1, source, target, spare );
            System.out.println(source + " => " + target);
            solveTowers( numOfDiscs - 1, spare, source, target );
        }
    }
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.err.println("Enter the number of discs: ");
        int numberOfDiscs = scan.nextInt();
        solveTowers(numberOfDiscs, SOURCE, SPARE, TARGET);
    }
            
    
}
