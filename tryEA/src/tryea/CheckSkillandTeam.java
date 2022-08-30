/*
 * THANIDA PRASERT
 * ID.1686951
 * (1+1)EA AND SIMULATOR
 * CheckSkillandTeam
 */
package tryea;

/**
 *
 * @author pang
 */
public class CheckSkillandTeam {
    int skillT=2;
    int numTeam=3;
    int p=0;
        //set skills, have 2 skills per a team;
    int[][] team={{1,2},
                {1,3},
                {1,3}};
    
        
        //check setted skills of plan with skills in the teams.
        public int Teams(int[] skill,int[] individual){
            
            for(int i=(individual.length/2);i<individual.length;i++){  //6 round = each wp
                int count = 0; 
                for(int j=0;j<numTeam;j++){  //teams
                    
                    for(int k=0;k<skillT;k++){  
                        if(individual[i]==(j+1)){
                            if(skill[i-(individual.length/2)]==team[j][k]){
                               // System.out.println(individual[i]+ " team "+ (j+1) +" can doing skill "+skill[i-(individual.length/2)]);
                                count++;
                            }
                        }
                    }
                }
                if(count==0){
                    p++;
                }
                
            //System.out.println("penalty in wp :: "+p+" ");
            }    
            System.out.println("penalty1 :: "+p);
            return p;
    }
}
