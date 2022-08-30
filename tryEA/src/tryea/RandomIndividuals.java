/*
 * THANIDA PRASERT
 * ID.1686951
 * (1+1)EA AND SIMULATOR
 * RandomIndividuals
 */
package tryea;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author pang
 */
public class RandomIndividuals {
    int numTeam=3;
    int RandTime=30;
    
    public int[] RandomIndividual(int numWP) {
        int[] individual = new int[numWP * 2];
        Random rand = new Random();
        for(int i=0;i<numWP * 2;i++){
            if(i<numWP){
                individual[i]=rand.nextInt(RandTime);
            }else{
                individual[i]=rand.nextInt(numTeam)+1; // 3 team
            }
        }
        System.out.println(Arrays.toString(individual));
        return individual;
    }
    
}
