/*
 * THANIDA PRASERT
 * ID.1686951
 * (1+1)EA AND SIMULATOR
 * Duration
 */
package tryea;

import java.util.Random;

/**
 *
 * @author pang
 */
public class Duration {
    int d;
    int longest=9;
    public int CheckDuration(int[] duration, int[] individual){
        int a=0;
        d=individual[0]+ duration[0];
        for(int i=0; i<individual.length/2;i++){
            a=individual[i]+ duration[i];
            if(a>=d){
                d=a;
            }d=d;
        }
        
        return d;
        
    }
    
}
