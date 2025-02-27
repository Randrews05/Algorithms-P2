/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package MakingChange;

import java.util.Scanner;

/**
 * This class uses a bottum up method to solve the "Making Change" problem
 * 
 * @author Riley Andrews & Myles Young
 * @version 1.0
 * File: BUp.java
 * Created: Feb 2025
 * @Copyright Cedarville University, its Computer Science faculty,
 * and the authors. All rights reserved.
 * Description: This class creates an algorithm based off of a bottom up solution
 * to handle the "Making Change" problem. The program increments over an array
 * to get the optimal solution to every answer before the desired value.
 */
public class BUp {
    
    /**
     * Increments through an array, bottom up, to solve the "Making Change" problem
     * @param bestCoin Array of Purses to hold all of the best possible combinations
     *                 of coins for each value
     * @param currValue The value that is being tested
     * @param denoms Array of integers that holds the denominations given
     * @return The Purse which holds the best possible combination of coins
     */
    public static Purse BuildUp(Purse[] bestCoin, int currValue, int[] denoms){
        for(int j = 1; j < currValue + 1; j++) { //Gets into each purse
            bestCoin[j] = new Purse(denoms.length, currValue + 1); //Makes the purse in that spot in the array
            for(int i = 0; i < denoms.length; i++){ //Goes through the denominations in the array
                    int coin = denoms[i];
                    if(coin <= j) { //If the coin is less than or equal to the current
                        int r = j - coin; //Remaing coins
                        if(bestCoin[r].getTotal() + 1 < bestCoin[j].getTotal()){ //Have to check and see if it is worth doing     
                            bestCoin[j] = new Purse(bestCoin[r]); //Clones other purse
                            bestCoin[j].incriment(i); //Incriments by one
                        }
                    }
                }
            }  
        
        return bestCoin[currValue]; //Returns the Purse with the optimal solution
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
        for(int i = 0; i < k; i++){
            int currValue = scanner.nextInt();
            Purse[] bestCoin = new Purse[currValue + 1];//Create Array full of Purses
            bestCoin[0] = new Purse(denoms.length, 0); //Default
            long start = System.nanoTime(); //Gets the time before Memoization
            bestCases[i] = BuildUp(bestCoin, currValue, denoms); //Call Memoization method
            long end = System.nanoTime(); //Gets the time after Memoization
            times[i] = end - start; //Adds the time to an array
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
            System.out.println(" Calculated Time(ns): " + times[i]); //Prints the calculated time
        }
    }
}
