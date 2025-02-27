/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MakingChange;

import java.util.Arrays;

/**
 * This class creates a Purse for the Memory Change Problem
 * 
 * @author Riley Andrews & Myles Young
 * @version 1.0
 * File: Purse.java
 * Created: Feb 2025
 * @Copyright Cedarville University, its Computer Science faculty,
 * and the authors. All rights reserved.
 * Description: This class creates a purse which holds the amount of total coins
 * used in an optimal solution and an array of the amount of each coin denomination
 * used
 */
public class Purse {
    private int totalCoins; //Total number of coins in the purse
    private int[] numCoins; //Array for number of Coins
    
    /**
     * Constructor
     */
    public Purse() { 
        this(0,-1);
    }
    
    /**
     * Constructor to set all the values to the given inputs
     * @param nod The amount of denominations there are in the problem
     * @param coins The total amount of coins in the given value
     */
    public Purse(int nod, int coins) {
        numCoins = new int[nod]; //Array the size of how many denoms there are
        Arrays.copyOf(numCoins, 0);
        this.totalCoins = coins; //Starts the coins at the highest value with pennies
    }
    
    /**
     * Copy Method
     * @param two Purse to be copied to this
     */
    public Purse(Purse two) { //Way to clone and update Purses
        this.numCoins = Arrays.copyOf(two.numCoins, two.numCoins.length);
        totalCoins = two.totalCoins;
    }
    
    /**
     * Increments the total coins and the specific denomination total
     * @param index What denomination to increase
     */
    public void incriment(int index) {
        totalCoins += 1;
        numCoins[index] += 1;
    }
    
    /**
     * Gets the total amount of coins
     * @return the total amount of coins
     */
    public int getTotal(){
        return totalCoins;
    }
    
    /**
     * Gets the number of denominations in the specific location
     * @param index What denomination to grab
     * @return Number of denominations in that specific location
     */
    public int getNumDenoms(int index){
        return numCoins[index];
    }
}
