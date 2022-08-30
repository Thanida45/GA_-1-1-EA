/*
 * THANIDA PRASERT
 * ID.1686951
 * GA AND SIMULATOR
 * MIAN CLASS try GA in general
 */
package tryga;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author pang
 */
public class TryGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //set resources.
        int numWP = 6; // can change more and also add other resources.
        int[] wpDuration = {3, 5, 9, 6, 6, 3}; // You can change more duration.
        int[] starting = {12, 15, 20, 0, 6, 29}; // starting times are assigned in the sample plan. You can change it as (0-100).
        int[] teams = {1, 2, 3, 1, 1, 3}; // teams are assigned in the sample plan. You can change it as (1 or 2 or 3).
        int[] skill = {2, 1, 1, 2, 2, 3};
        int longestDuration=0;   // change for the robustness in case of the noises : 0 , 10, 20, 30, 40, 50, and 60
        int t=0;
        int Tmax = 2000; //iterative can change from 1-1000...
        int[] records =new int[Tmax]; 
        int betterDuration=1000;  
        //add dependencies addDependency(wp, from wp(i).wp, to wp(j).wp)   wp(i)--> wp(j)
        
        int [][] depend1={    //6*6  wp*wp
            {0,1,0,0,0,0},  //wp1-->wpi
            {0,0,1,0,0,0},  //wp2-->wpi
            {0,0,0,0,0,1},  //wp3-->wpi
            {0,0,0,0,1,0},  //wp4-->wpi
            {0,0,0,0,0,0},  //wp5/
            {0,0,0,0,0,0}  }; //wp6///...
        //System.out.println("Dependencies :: " + Arrays.toString(depend));            //get dependencies from wp1 to wp numWP

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
        //Random
        //......
        RandomIndividuals r= new RandomIndividuals();
        int[] individual1 = r.RandomIndividual(numWP); 
        int[] individual2 = r.RandomIndividual(numWP); 
        int[] Betterindividual= new int[firstSample.length];
        
        for( t = 1; t <= Tmax; t++) {

            System.out.println("individual1 :: " + Arrays.toString(individual1));            //get dependencies from wp1 to wp numWP
            System.out.println("individual2 :: " + Arrays.toString(individual2)+"\n\n");

            System.out.println("Compute individual1 :: " + Arrays.toString(individual1));            //get dependencies from wp1 to wp numWP   
            int du1= fitness( wpDuration, skill, depend1, individual1);
            
            System.out.println("Compute individual2 :: " + Arrays.toString(individual2));
            int du2= fitness( wpDuration, skill, depend1, individual2);
            
            //for adding noises for consider robustness
            Random ran= new Random();
            int ra=ran.nextInt(longestDuration+1);
            System.out.println("noised"+du2+"\t"+ra);
            du2=du2+ra;
            
            //evaluate for next generation
            if (du1<=betterDuration&&du1<=du2) {
                Betterindividual = individual1;
                records[t-1]=du1;
                betterDuration = du1;
                System.out.println("no : " + t + " :" + du1 + " completion time of x :" + Arrays.toString(individual1)+"\n");
            } 
            
            if (du2<=betterDuration&&du2<=du1){
                Betterindividual = individual2;
                records[t-1]=du2;
                betterDuration=du2;
                System.out.println("no : " + t + " :" + du2 + " completion time of x':" + Arrays.toString(individual2)+"\n");             //next = newindividual
            }
            if (betterDuration<=du1&&betterDuration<=du2){
                Betterindividual = Betterindividual;
                records[t-1]=betterDuration;
                betterDuration=betterDuration;
                System.out.println("no : " + t + " :" + betterDuration + " completion time of x':" + Arrays.toString(Betterindividual)+"\n");             //next = newindividual
            }
            if(t==Tmax){
             System.out.println(Tmax+" completion times f: " + Arrays.toString(records));             
             int aver=0;
             for(int i=0;i<Tmax;i++){
                aver= aver+records[i];
                System.out.println((i+1)+" itertions has the completion time at "+records[i]+" the plan : "+Arrays.toString(Betterindividual));//                 aver= aver+records[i];
             }
             
             System.out.println("Averages of the fitnesses :"+aver/Tmax);
        }else
            {
            
            Crossover cross= new Crossover();
            Mutation m = new Mutation();
            individual1 = m.mutate(cross.Crossover(individual1, individual2));
            }
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
