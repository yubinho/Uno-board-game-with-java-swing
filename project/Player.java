import java.util.ArrayList;

public class Player {


    private ArrayList<UnoCard> handCard = new ArrayList<>();
    private String playerName;
    private Boolean isUno = false;

    public Player(String playerName){
        this.playerName = playerName;

    }

    public ArrayList<UnoCard> getHandCard() {
        return handCard;
    }

    public void setHandCard(ArrayList<UnoCard> handCard) {
        this.handCard = handCard;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerHandCardSize(){
        return handCard.size();
    }

    public UnoCard getPlayerCard(int choice){
        return handCard.get(choice);
    }

    public boolean hasEmptyHandCard(){
        return handCard.isEmpty();
    }

    public boolean getPlayerUnoJudgement(){
        return isUno;
    } 
    
    public void setPlayerUnoJudgement(boolean isUno){
        this.isUno = isUno;
    }
    public String getUnoStatement(){
        if(isUno){
            return "yes";
        }else if(!isUno){
            return "no";
        }
        return "";
    }
}
