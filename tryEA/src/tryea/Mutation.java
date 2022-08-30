/*
 * THANIDA PRASERT
 * ID.1686951
 * (1+1)EA AND SIMULATOR
 * Mutation
 */
package tryea;

import java.util.Random;

/**
 *
 * @author pang
 */
public class Mutation {
    int maxTime=30;
    int maxTeam=3;
    
    public int[] mutate(int[] individual){
        //mutate one point at starting part.
        for(int i=0;i<individual.length/2;i++){
            Random r = new Random();
            individual[i]=r.nextInt(maxTime);
        }
        for(int j=individual.length/2;j<individual.length;j++){
            Random r = new Random();
            individual[j]=r.nextInt(maxTeam)+1;
        }
      
        return individual;
    }
}
