/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MakingChange;


import java.util.Scanner;

/**
 * This class uses recursion to solve the "Making Change" problem
 * 
 * @author Riley Andrews & Myles Young
 * @version 1.0
 * File: nonMemo.java
 * Created: Feb 2025
 * @Copyright Cedarville University, its Computer Science faculty,
 * and the authors. All rights reserved.
 * Description: This class creates an algorithm based off of recursion
 * to solve the "Making Change" problem. The program works top down, so
 * it recursively calls the best answer for the current value and continues until
 * the problem is solved without saving data using memoization
 */

public class nonMemo {
    
    /**
     * Performs recursion to solve the "Making Change" problem
     * @param currValue The value that is being tested
     * @param denoms Array of integers that holds the denominations given
     * @return The Purse which holds the best possible combination of coins
     */
    public static Purse Recursion(int currValue, int[] denoms){ //Without Memoization
        if(currValue ==0) { //Basis Step
            return new Purse(denoms.length, 0);
        }
        //This is where memoization step would go
        //Recurssive Call
        Purse bestCase = new Purse(denoms.length, currValue + 1); //Put everything to a high number
        int i = 0; //Keeps track of the index of the denom array
        for(int coins : denoms){ //Iterates through all the denominations
            if(coins <= currValue){ //Compares them to the current value
                Purse temp = Recursion(currValue - coins, denoms); //Recursion step
                if(temp.getTotal() + 1 < bestCase.getTotal()){
                    bestCase = new Purse(temp); //Creates a clone of the temp Purse
                    bestCase.incriment(i); //Incriments the values
                }
            }
            i++;
        }
        return bestCase;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Define Scanner
        
        int n = scanner.nextInt(); //Number of different denominations
        int[] denoms = new int[n]; //Array of the type of denominations
        for (int i = 0; i < n; i++) {
            denoms[i] = scanner.nextInt();
        }
        int k = scanner.nextInt(); //Number of different money values to be solved        
        Purse[] bestCases = new Purse[k];
        int[] vals = new int[k];
        long[] times = new long[k]; //Keep track of times
        for (int i = 0; i < k; i++) { //Recusion without memoization
            int currValue = scanner.nextInt();
            long start = System.nanoTime(); //Gets the times before Recursion
            bestCases[i] = Recursion(currValue, denoms); //Call Recursion method
            System.out.print(i);
            long end = System.nanoTime(); //Gets the time after Recursion
            times[i] = end - start; 
            vals[i] = currValue; //Keep track of values
        }
        //Print
        for(int i = 0; i < k; i++){
            System.out.print(vals[i] + " cents =");
            for(int j = denoms.length - 1; j >=0; j--){
                if(bestCases[i].getNumDenoms(j) != 0){ //If the denomination was used, then print
                    System.out.print(" " + denoms[j] + ":" + bestCases[i].getNumDenoms(j));
                }
            }
            System.out.println(" Calculated Time(ns): " + times[i]);
        }
    }
}
