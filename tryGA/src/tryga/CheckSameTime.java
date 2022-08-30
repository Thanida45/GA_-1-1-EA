/*
 * THANIDA PRASERT
 * ID.1686951
 * (1+1)EA AND SIMULATOR
 * check same time
 */
package tryga;

import java.util.Arrays;

/**
 *
 * @author pang
 */
public class CheckSameTime {
    int p=0;
    int maxTime=300;
    int team=3;
    int[][] check= new int[team][maxTime];
    
    
    public int CheckSameTime(int[] duration, int[] individual){
             //System.out.println(Arrays.toString(check));  

            for(int i=1;i<=team;i++){     // check each team
                // check wpi
                for( int j=0;j<individual.length/2;j++){   // wpi
                    if(individual[j+individual.length/2]==i){                       //which team
                            int total= individual[j]+duration[j];
                            for(int k=individual[j];k<total;k++){
                                    if(check[i-1][k]!=0){
                                    p++;
                                    }
                                    
                                    check[i-1][k]=j+1;
                            }
                    }   
                }
            }
            
            /*     for(int j=0;j<maxTime;j++){
                System.out.print(check[0][j]);
            }System.out.println();
             for(int j=0;j<maxTime;j++){
                System.out.print(check[1][j]);
            } System.out.println();
             for(int j=0;j<maxTime;j++){
                System.out.print(check[2][j]);
            }System.out.println();
            */
            System.out.println("penalty2 :: "+p);
            return p;
        }
    
}
