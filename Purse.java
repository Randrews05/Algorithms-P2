/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MakingChange;

import java.util.Arrays;

/**
 *
 * @author Dell
 */
public class Purse {
    private int totalCoins; //Total number of coins in the purse
    private int[] numCoins; //Array for number of Coins
    
    
    public Purse() { //Constructor
        this(0,-1);
    }
    
    public Purse(int nod, int coins) {
        numCoins = new int[nod]; //Array the size of how many denoms there are
        Arrays.copyOf(numCoins, 0);
        this.totalCoins = coins; //Starts the coins at the highest value with pennies
    }
    public void newNumCoins(int[] denoms){
        numCoins = Arrays.copyOf(denoms, denoms.length);
    }
    
    public void incriment(int index) {
        totalCoins += 1;
        numCoins[index] += 1;
    }
    
    public void setTotal(int val) {
        totalCoins = val;
    }
    
    public int getTotal(){
        return totalCoins;
    }
    public int getNumDenoms(int index){
        return numCoins[index];
    }
    public int[] getNumCoins(){
        return numCoins;
    }
    public void newClone(Purse two) { //Way to clone and update Purses
        System.arraycopy(two.numCoins, 0, this.numCoins, 0, two.numCoins.length);
        totalCoins = two.totalCoins;
    }
}
