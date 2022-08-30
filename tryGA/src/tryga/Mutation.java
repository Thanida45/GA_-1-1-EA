/*
 * THANIDA PRASERT
 * ID.1686951
 * GA AND SIMULATOR
 * Mutation
 */
package tryga;

import java.util.Random;

/**
 *
 * @author pang
 */
public class Mutation {
    int RandTime=30;
    
    public int[] mutate(int[] individual){
        //mutate one point at starting part.
        Random r = new Random();
        int point= r.nextInt((individual.length/3)+1);
        int temp;
        //System.out.println(point+" value is "+individual[point]);  
        temp = individual[point];
        individual[point] = individual[point+1];
        individual[point+1] = r.nextInt(RandTime);
        return individual;
    }
}
