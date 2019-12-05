
package animalfarm;

/**
 *
 * @SyamimiRazmin
 */
public class CattlesInfo {
    private String cattleID; 
    private String weight; 
    private String breedType; 
    private String dateRegistered;
    
    
    CattlesInfo(String cattleID, String weight, String breedType, String dateRegistered){
        this.cattleID = cattleID;
        this.weight = weight;
        this.breedType = breedType;
        this.dateRegistered = dateRegistered;
    }
    
    void setweight(String weight){
        this.weight = weight;
    }
    
    void setbreedType(String breedType){
        this.breedType = breedType;
    }
    
    void setdateRegister(String dateRegister){
        this.dateRegistered = dateRegistered;
    }
    
    String getcattleID(){
        return cattleID;
    }
    
    String getweight(){
        return weight;
    }
    
    String getbreedType(){
        return breedType;
    }
    
    String getdateRegister(){
        return dateRegistered;
    }
}


