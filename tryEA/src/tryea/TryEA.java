/*
 * THANIDA PRASERT
 * ID.1686951
 * (1+1)EA AND SIMULATOR
 * MIAN CLASS try EA in general
 */
package tryea;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author pang
 */
public class TryEA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //set resources.
        int numWP = 6; // can change more and also add other resources.
        int[] wpDuration = {3, 5, 9, 6, 6, 3}; //if change more duration, it is max to 100 days
        int[] starting = {12, 15, 20, 0, 6, 29}; //if change more starting, it is max to i days + i duration not
        int[] teams = {1, 2, 3, 1, 1, 3};
        int[] skill = {2, 1, 1, 2, 2, 3};
        int longestDuration=0; // change for the robustness in case of the noises : 0 , 10, 20, 30, 40, 50, and 60
        int t=0;
        int Tmax = 2000; //iterative can change from 1-1000...
        int[] records =new int[Tmax];
        
        //add dependencies addDependency(wp, from wp(i).wp, to wp(j).wp)   wp(i)--> wp(j)
        //ex. addDependency(numWP,depend,start which number of WP,to which number of WP)
        
        int [][] depend1={    //6*6  wp*wp
            {0,1,0,0,0,0},  //wp1--> which wp
            {0,0,1,0,0,0},  //wp2-->which wp
            {0,0,0,0,0,1},  //wp3-->which wp
            {0,0,0,0,1,0},  //wp4-->which wp
            {0,0,0,0,0,0},  //wp5-->which wp
            {0,0,0,0,0,0}  }; //wp6-->which wp
        
        //Sample individual (No mutation)
        int[] firstSample = creatFirstIndividual(numWP, starting, teams);
        CheckSkillandTeam ti = new CheckSkillandTeam();
        int p01 = ti.Teams(skill, firstSample);
        CheckSameTime s = new CheckSameTime();
        int p02 = s.CheckSameTime(wpDuration, firstSample);
        CheckDepend d = new CheckDepend();
        int p03 = d.CheckDepend(wpDuration, depend1, firstSample);
        Duration c = new Duration();
        int du = c.CheckDuration(wpDuration, firstSample);
        int    dura = du + p01 + p02 + p03;
        System.out.println("fitness1 :: " + du + " " + p01 + " " + p02 + " " + p03+" = " +dura +"\n\n\n RANDOM NOW \n\n\n\n");
        
        //Random individuals
        //random
        RandomIndividuals r= new RandomIndividuals();
        int[] individual = r.RandomIndividual(numWP); 
        //System.out.println("try : " +Arrays.toString(tryEA)+" \n\n");
        for( t = 1; t <= Tmax; t++) {
            
            int[] clonx = individual.clone();
            //mutation the individual one point.
            //X' individual
            Mutation m = new Mutation();
            int[] newindividual = m.mutate(clonx);
            
            System.out.println("oldindividual :: " + Arrays.toString(individual));            //get dependencies from wp1 to wp numWP
            System.out.println("newindividual :: " + Arrays.toString(newindividual)+"\n\n");

            System.out.println("Compute oldindividual :: " + Arrays.toString(individual));            //get dependencies from wp1 to wp numWP   
            int du1= fitness( wpDuration, skill, depend1, individual);
            System.out.println("Compute newindividual :: " + Arrays.toString(newindividual));
            int du2= fitness( wpDuration, skill, depend1, newindividual);
            Random ran= new Random();
            //noise for consider robustness
            int ra=ran.nextInt(longestDuration+1);
            System.out.println("noised"+du2+"\t"+ra);
            du2=du2+ra;
              
            if (du1 <= du2) {
                individual = individual;
                records[t-1]=du1;
                System.out.println("no : " + t + " :" + du1 + " completion time of x :" + Arrays.toString(individual)+"\n");
            } else {
                individual = newindividual;
                records[t-1]=du2;
                System.out.println("no : " + t + " :" + du2 + " completion time of x':" + Arrays.toString(newindividual)+"\n");             //next = newindividual
            }
            if(t==Tmax){
             System.out.println("The last iteration: "+Tmax+" completion times f: " + Arrays.toString(records));             //next = newindividual
             int aver=0;
             for(int i=0;i<Tmax;i++){
                 aver= aver+records[i];
                 System.out.println((i+1)+" itertions has the completion time at "+records[i]+" the plan : "+Arrays.toString(individual));//
             }
             
             System.out.println("Averages of the fitnesses :"+aver/Tmax);
        }else;
        }
    }

    
    public static int fitness( int[] wpDuration, int[] skill, int[][] depend1, int[] individual){
        int fitness=0;
        CheckSkillandTeam ti = new CheckSkillandTeam();
        int p01 = ti.Teams(skill, individual);
        CheckSameTime s = new CheckSameTime();
        int p02 = s.CheckSameTime(wpDuration,individual );
        CheckDepend d = new CheckDepend();
        int p03 = d.CheckDepend(wpDuration, depend1,individual );
        Duration c = new Duration();
        int du = c.CheckDuration(wpDuration,individual );
        fitness = du + p01 + p02 + p03;
        System.out.println("duration + penalty1 + penalty2 + penalty3 : " + du + "+" + p01 + "+" + p02 + "+" + p03);
        System.out.println("Fitness : "+fitness+"\n");        
        return fitness;
    }
    
    public static int[] creatFirstIndividual(int numWP, int[] starting, int[] teams) {
        int[] individual = new int[numWP * 2];
        for (int i = 0; i < numWP; i++) {
            individual[i] = starting[i];
            individual[i + numWP] = teams[i];
        }
        System.out.println(Arrays.toString(individual));
        return individual;
    }

    

}
