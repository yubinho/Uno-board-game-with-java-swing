import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class UnoDeck {

    private UnoCard[] cards;
    private int cardsInDeck;

    /***************
     * 共108張牌
     * 顏色分為紅黃綠藍
     * '0' 各有一張, '1' ~ '9' 則各有2張, 'skip' x2, 'draw two' x2, 'reverse' x2
     * 並有 4 張 wild, 4 張 Wild four
     *  *************/

    public UnoDeck(){
        cards = new UnoCard[108];
        reset();
    }

    public void reset(){
        UnoCard.Color[] colors = UnoCard.Color.values();    //Red,Blue,Green,Yellow
        cardsInDeck = 0;

        for(int i=0;i<colors.length-1;i++){
            UnoCard.Color color = colors[i];
            cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(0));   //add zero

            for(int j=1;j<10;j++){
                cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));   // add 1 ~ 9 
                cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));    
            }

            UnoCard.Value[] values = new UnoCard.Value[]{
                UnoCard.Value.DrawTwo,UnoCard.Value.Skip,UnoCard.Value.Reverse};
            for(UnoCard.Value value:values){
                cards[cardsInDeck++] = new UnoCard(color, value);       //add Reverse, Skip, DrawTwo
                cards[cardsInDeck++] = new UnoCard(color, value);
            }
        }
        UnoCard.Value[] wildValues = new UnoCard.Value[]{
            UnoCard.Value.Wild_Four,UnoCard.Value.Wild};
        
        for(UnoCard.Value value:wildValues){

            for(int k=0;k<4;k++){
                cards[cardsInDeck++] = new UnoCard(UnoCard.Color.Wild, value); //add Wild, Wild four *4 for each
            }
        }
    }

    public void replaceDeckWith(ArrayList<UnoCard> cards){
        this.cards = cards.toArray(new UnoCard[cards.size()]);  //turning stockpile ArrayList to Array
        cardsInDeck = this.cards.length;
    }
    public boolean isEmpty(){
        if(cardsInDeck == 0){
            return true;
        }
        return false;
    }

    public void shuffle(){
        int n = cards.length;
        Random random = new Random();
        for(int i=0;i<cards.length;i++){
            int randomValue = i + random.nextInt(n-i);
            UnoCard randomCard = cards[randomValue];
            cards[randomValue] = cards[i];      //swap cards to shuffle
            cards[i] = randomCard;
        }
        
    }

    public UnoCard drawCard() throws IllegalArgumentException{
        if(isEmpty()){
            throw new IllegalArgumentException("there are no cards in the deck");
        }
        return cards[--cardsInDeck];           // draw from the top of the deck
    }

    public UnoCard[] drawCard(int n) throws IllegalArgumentException{
        if(n < 0){
            throw new IllegalArgumentException("Must draw positive card, but draw "+n+" card");
        }
        if(n > cardsInDeck){
            throw new IllegalArgumentException("cannot draw "+n+" cards since there are only "+cardsInDeck+" cards");
        }
        UnoCard[] cardArray = new UnoCard[n];
        for(int i=0;i<n;i++){
            cardArray[i] = cards[--cardsInDeck];
        }
        return cardArray;
    }

    public ImageIcon drawCardImage() throws IllegalArgumentException{
        if(isEmpty()){
            throw new IllegalArgumentException("there are no cards in the deck");
        }
        return new ImageIcon("picture/small/"+cards[--cardsInDeck].toString()+".png");
    }
}
