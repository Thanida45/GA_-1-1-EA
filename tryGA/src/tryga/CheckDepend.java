/*
 * THANIDA PRASERT
 * ID.1686951
 * GA AND SIMULATOR
 * CheckDependdency
 */
package tryga;

/**
 *
 * @author pang
 */
public class CheckDepend {
    int p=0;
    
    public int CheckDepend(int[] duration,int[][] dependency, int[] individual){
           for(int i=0;i<duration.length;i++){
                 for(int j=0;j<duration.length;j++){
                     if(dependency[i][j]==1){
                         int a=individual[j];       // Starting time of next indivudual
                            if((individual[i]+duration[i])>individual[j]){
                            //System.out.println("WP"+(i+1)+" has dependency to next WP"+(j+1)+" WRONG");
                            p += (individual[i]+duration[i])-individual[j];
                     }else{ // x<=y
                        //System.out.println("WP"+(i+1)+" has dependency to next WP"+(j+1)+" CORRECT");
                    }
                     
                 }
            } 
                 //System.out.println(p);
            
            }
                System.out.println("penalty3 :: "+p);

        return p;
        
    }
}
