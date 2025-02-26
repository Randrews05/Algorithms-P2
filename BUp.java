/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package MakingChange;

import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class BUp {

    /**
     *
     * @param k
     * @param currValue
     * @param denoms
     */
    public static String buildUp(int k, int currValue, int[] denoms){
        Purse[] bestCoin = new Purse[currValue + 1];//Create Array full of Purses
        bestCoin[0] = new Purse(denoms.length, 0); //Default
        for(int j = 1; j < currValue + 1; j++) { //Gets into each purse
            bestCoin[j] = new Purse(denoms.length, currValue + 1); //Makes the purse in that spot in the array
            for(int i = 0; i < denoms.length; i++){ //Goes through the denominations in the array
                    int coin = denoms[i];
                    if(coin <= j) { //If the coin is less than or equal to the current
                        int r = j - coin; //Remaing coins
                        if(bestCoin[r].getTotal() + 1 < bestCoin[j].getTotal()){ //Have to check and see if it is worth doing     
                            bestCoin[j].newClone(bestCoin[r]);
                            bestCoin[j].incriment(i); 
                        }
                    }
                }
            }  
        //Print Statement
        String answer = currValue + " cents =";
        for(int i = denoms.length - 1; i >= 0; i--){
            if(bestCoin[currValue].getNumDenoms(i) != 0){
                answer += (" " + denoms[i] + ":" + bestCoin[currValue].getNumDenoms(i));
            }
        }
        return answer;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Define Scanner
        
        int n = scanner.nextInt(); //Number of different denominations
        int[] denoms = new int[n]; //Array of the type of denominations
        for (int i = 0; i < n; i++) {
            denoms[i] = scanner.nextInt();
        }
        int k = scanner.nextInt(); //Number of different money values to be solved        
        String[] answers = new String[k];
        for(int i = 0; i < k; i++){
            int currValue = scanner.nextInt();
            answers[i] = buildUp(k, currValue, denoms);
        }
        for(int i = 0; i < k; i++){
            System.out.println(answers[i]);
        }
    }
}
