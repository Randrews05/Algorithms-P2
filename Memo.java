/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MakingChange;

import java.util.Scanner;

/**
 * This class uses Memoization to solve the "Making Change" problem
 * 
 * @author Riley Andrews & Myles Young
 * @version 1.0
 * File: Memo.java
 * Created: Feb 2025
 * @Copyright Cedarville University, its Computer Science faculty,
 * and the authors. All rights reserved.
 * Description: This class creates an algorithm based off of recursion and memoization
 * to handle the "Making Change" problem. The program works top down, so
 * it recursively calls the best answer for the current value and continues until
 * the problem is solved while saving data using Memoization.
 */

public class Memo {
    
    /**
     * Performs Memoization to solve the "Making Change" problem
     * @param bestCoin Array of Purses to hold all of the best possible combinations
     *                 of coins for each value
     * @param currValue The value that is being tested
     * @param denoms Array of integers that holds the denominations given
     * @return The Purse which holds the best possible combination of coins
     */
    public static Purse Memoization(Purse[] bestCoin, int currValue, int[] denoms){ //Without Memo
        if(currValue ==0) { //Basis Step
            return new Purse(denoms.length, 0);
        }
        //Memoization Step
        if(bestCoin[currValue] != null){ //If it isn't null
            return bestCoin[currValue]; //Return the already calculated value
        }
        
        //Recurssive Call
        Purse bestCase = new Purse(denoms.length, currValue + 1); //Put everything to a high number
        int i = 0; //Keep track of the index of the denom array
        for(int coins : denoms){ //Iterates through all the denominations
            if(coins <= currValue){ //Compares them to the current value
                Purse temp = Memoization(bestCoin, currValue - coins, denoms);
                if(temp.getTotal() + 1 < bestCase.getTotal()){ //If new value is better than before
                    bestCase = new Purse(temp); //Create a clone of the temp Purse
                    bestCase.incriment(i); //Incriment the values
                }
            }
            i++;
        }
        //Memoization step 2
        bestCoin[currValue] = new Purse(bestCase); //Add Purse to the bestCoin array to keep track of it
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
        for (int i = 0; i < k; i++) { //Recusion with memoization
            int currValue = scanner.nextInt();
            Purse[] bestCoin = new Purse[currValue + 1];//Create Array full of Purses
            //bestCoin[0] = new Purse(denoms.length, 0); //Default
            long start = System.nanoTime(); //Gets the time before Memoization
            bestCases[i] = Memoization(bestCoin, currValue, denoms); //Call Memoization method
            long end = System.nanoTime(); //Gets the time after Memoization
            times[i] = end - start;
            vals[i] = currValue; //Keep track of values inputed
        }
        
        //Print 
        for(int i = 0; i < k; i++){
            System.out.print(vals[i] + " cents =");
            for(int j = denoms.length - 1; j >=0; j--){
                if(bestCases[i].getNumDenoms(j) != 0){ //If the denomination was used, then print it
                    System.out.print(" " + denoms[j] + ":" + bestCases[i].getNumDenoms(j));
                }
            }
            System.out.println(" Calculated Time(ns): " + times[i]);
        }
    }
}
