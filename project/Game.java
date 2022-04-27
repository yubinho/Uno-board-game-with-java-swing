import java.util.*;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game {

    private int currentPlayer = 0;
    private Player[] players;
    private ArrayList<UnoCard> currentHandCard = new ArrayList<>();
    private ArrayList<UnoCard> stockpile = new ArrayList<>();
    private UnoDeck deck;

    private UnoCard.Color validColor;
    private UnoCard.Value validValue;

    boolean direction = false;

    GameStage gameStage;

    public Game(String[] playerName) {

        deck = new UnoDeck();
        deck.shuffle();
        this.players = new Player[playerName.length];

        for (int i = 0; i < players.length; i++) { // set player name for each player
            players[i] = new Player(playerName[i]);
        }

        for (int i = 0; i < players.length; i++) { // add seven cards to each player
            ArrayList<UnoCard> handCard = new ArrayList<>(Arrays.asList(deck.drawCard(3)));
            players[i].setHandCard(handCard);
        }
    }

    public void start(Game game, GameStage gameStage) {
        this.gameStage = gameStage;
        UnoCard card = deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

        if (card.getValue() == UnoCard.Value.Wild || card.getValue() == UnoCard.Value.DrawTwo
                || card.getValue() == UnoCard.Value.Wild_Four) {
            start(game, gameStage); // restart
        }
        if (card.getValue() == UnoCard.Value.Skip) {
            JLabel message = new JLabel(players[currentPlayer].getPlayerName() + " 被跳過了");
            JOptionPane.showMessageDialog(gameStage.getContentPane(), message);
            nextPlayer();
        }
        if (card.getValue() == UnoCard.Value.Reverse) {
            JLabel message = new JLabel(players[currentPlayer].getPlayerName() + " 更改遊戲方向");
            JOptionPane.showMessageDialog(gameStage.getContentPane(), message);
            direction ^= true; // true --> false, false --> true
            nextPlayer();
        }
        stockpile.add(card);
    }

    public void nextPlayer() {
        if (direction == false) {
            currentPlayer = (currentPlayer + 1) % players.length;
        } else if (direction == true) {
            currentPlayer = (currentPlayer - 1) % players.length;
            if (currentPlayer == -1) {
                currentPlayer = players.length - 1;
            }
        }
    }

    public UnoCard getTopCard() {
        return new UnoCard(validColor, validValue);
    }

    public ImageIcon getTopCardImage() {
        return new ImageIcon("picture/small/" + validColor + "_" + validValue + ".png");
    }

    public boolean isGameOver() {
        for (Player player : players) {
            if (player.hasEmptyHandCard()) {
                return true;
            }
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public Player getPreviousPlayer() {
        int index = currentPlayer - 1;
        if (index == -1) {
            index = players.length - 1;
        }
        return players[index];
    }

    public boolean validCardPlay(UnoCard card) {
        return card.getColor() == validColor || card.getValue() == validValue;
    }

    public void checkPlayerTurn(Player player) throws InvalidPlayerTurnException {
        if (players[currentPlayer] != player) {
            throw new InvalidPlayerTurnException("目前非 " + player.getPlayerName() + " 的回合", player);
        }
    }

    public void submitDraws(Player player) throws InvalidPlayerTurnException {
        checkPlayerTurn(player);
        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockpile);
            deck.shuffle();
        }
        player.getHandCard().add(deck.drawCard());
        if (player.getHandCard().size() > 2) {
            player.setPlayerUnoJudgement(false);
        }
        nextPlayer();
    }

    public void setPlayerOver() {
        if (players[currentPlayer].getHandCard().size() <= 2) {
            players[currentPlayer].setPlayerUnoJudgement(true);
            JOptionPane.showMessageDialog(gameStage, players[currentPlayer].getPlayerName() + " 已經 Uno!");
        } else {
            JOptionPane.showMessageDialog(gameStage, "操作無效, 你目前還有兩張以上手牌");
        }

    }

    public void setValidColor(UnoCard.Color color) {
        validColor = color;
    }

    public void setValidValue(UnoCard.Value value) {
        validValue = value;
    }

    public void submitPlayerCard(Player player, UnoCard card, UnoCard.Color declaredColor)
            throws InvalidPlayerTurnException, InvalidColorSubmissionException, InvalidValueSubmissionException {

        boolean isCatchException = false;
        boolean isReverse = false;
        checkPlayerTurn(player);
        currentHandCard = player.getHandCard();
        /***
         * check card valid and isGameOver
         * code start
         ***/
        if (!validCardPlay(card)) {

            if (card.getColor() == UnoCard.Color.Wild) {
                validColor = card.getColor();
                validValue = card.getValue();
            }

            if (card.getColor() != validColor) {
                JLabel message = new JLabel(
                        "操作無效, 目前顏色: " + validColor + " ,您出的顏色: " + card.getColor());
                JOptionPane.showMessageDialog(gameStage, message);
                isCatchException = true;
                throw new InvalidColorSubmissionException(message, card.getColor(), validColor);
            } else if (card.getValue() != validValue) {
                JLabel message = new JLabel(
                        "操作無效, 目前數字: " + validValue + " ,您出的數字: " + card.getValue());
                JOptionPane.showMessageDialog(gameStage, message);
                isCatchException = true;
                throw new InvalidValueSubmissionException(message, card.getValue(), validValue);
            }
        }
        if (!isCatchException) {
            if (player.getHandCard().size() > 2) {
                player.setPlayerUnoJudgement(false);
            }
            currentHandCard.remove(card);
            player.setHandCard(currentHandCard);

            if (player.hasEmptyHandCard()) {
                if (player.getPlayerUnoJudgement() == true) {
                    // String playerName = players[currentPlayer].getPlayerName();
                    // JLabel message = new JLabel(playerName + " won the game!");
                    // JOptionPane.showMessageDialog(null, message);
                    WinnerFrame win = new WinnerFrame(player, gameStage);
                    win.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "懲罰! " + player.getPlayerName() + "沒有先喊Uno! ");
                    currentHandCard.add(deck.drawCard());
                    currentHandCard.add(deck.drawCard());
                    player.setHandCard(currentHandCard);
                }
                // System.exit(0);
            }

            validColor = card.getColor();
            validValue = card.getValue();

            stockpile.add(card);
            /***
             * check card valid and isGameOver
             * code end
             ***/

            /***
             * functional card to next player
             * code start
             ***/
            if (card.getValue() == UnoCard.Value.Reverse) {
                JLabel message = new JLabel(players[currentPlayer].getPlayerName() + " 更改遊戲方向");
                JOptionPane.showMessageDialog(null, message);
                isReverse = true;
                direction ^= true; // true --> false, false --> true
                nextPlayer();
            }
            if(!isReverse){
                nextPlayer();
            }
            currentHandCard = players[currentPlayer].getHandCard(); // next player hand

            if (card.getColor() == UnoCard.Color.Wild) {
                validColor = declaredColor;
            }
            if (card.getValue() == UnoCard.Value.DrawTwo) {
                currentHandCard.add(deck.drawCard());
                currentHandCard.add(deck.drawCard());
                players[currentPlayer].setHandCard(currentHandCard);
                JLabel message = new JLabel(players[currentPlayer].getPlayerName() + " 抽兩張牌");
                JOptionPane.showMessageDialog(null, message);
            }
            if (card.getValue() == UnoCard.Value.Wild_Four) {
                currentHandCard.add(deck.drawCard());
                currentHandCard.add(deck.drawCard());
                currentHandCard.add(deck.drawCard());
                currentHandCard.add(deck.drawCard());
                players[currentPlayer].setHandCard(currentHandCard);
                JLabel message = new JLabel(players[currentPlayer].getPlayerName() + " 抽四張牌");
                JOptionPane.showMessageDialog(null, message);
            }
            if (card.getValue() == UnoCard.Value.Skip) {
                JLabel message = new JLabel(players[currentPlayer].getPlayerName() + " 被跳過了");
                JOptionPane.showMessageDialog(null, message);
                nextPlayer();
            }
           
            // players[currentPlayer].setHandCard(currentHandCard);
            /***
             * functional card to next player
             * code end
             ***/
        }

    }

}

class InvalidPlayerTurnException extends Exception {
    String playerName;

    public InvalidPlayerTurnException(String message, Player player) {
        super(message);
        playerName = player.getPlayerName();
    }

    public String getPlayerName() {
        return playerName;
    }
}

class InvalidColorSubmissionException extends Exception {
    private UnoCard.Color actual;
    private UnoCard.Color valid;

    public InvalidColorSubmissionException(JLabel message, UnoCard.Color actual, UnoCard.Color valid) {
        this.actual = actual;
        this.valid = valid;
    }
}

class InvalidValueSubmissionException extends Exception {
    private UnoCard.Value actual;
    private UnoCard.Value valid;

    public InvalidValueSubmissionException(JLabel message, UnoCard.Value actual, UnoCard.Value valid) {
        this.actual = actual;
        this.valid = valid;
    }
}
