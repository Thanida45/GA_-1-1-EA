/*
 * THANIDA PRASERT
 * ID.1686951
 * GA AND SIMULATOR
 * Crossover
 */
package tryga;

import java.util.Arrays;

/**
 *
 * @author pang
 */
public class Crossover {
    
    public int[] Crossover(int[] individual1, int[] individual2){
        int[] individual=individual1;
        for(int i=0;i<6;i++){
               individual[i+6]=individual2[i+6];       
            }
            System.out.println("crossover : "+Arrays.toString(individual)+"\n\n end the iteration \n");
            return individual;
    }
    
            
}
