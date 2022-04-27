import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PopUp extends JFrame {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 650;

	private String cardImage;
	private ArrayList<UnoCard> playerHandCard = new ArrayList<>();
	private int choice;
	private ArrayList<JButton> cardButtons = new ArrayList<>();
	private UnoCard.Color declaredColor;
	JButton topCardButton;
	Game game;
	GameStage gameStage;

	public PopUp(String cardName, Game game, int index, ArrayList<JButton> cardButtons, GameStage gameStage,
			JButton topCardButton) {
		super("submitCardChecker");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel p = new JPanel();
		add(p, BorderLayout.SOUTH);

		JButton btn = new JButton("使用");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ChooseWildCardColor chooseColor = new ChooseWildCardColor(PopUp.this);
				declaredColor = chooseColor.choseColor(playerHandCard.get(choice));
				if (declaredColor != null) {
					try {
						game.submitPlayerCard(game.getCurrentPlayer(), game.getCurrentPlayer().getPlayerCard(choice),
								declaredColor);
						PopUp.this.revalidate();
						if (declaredColor != UnoCard.Color.Wild) {
							gameStage.setPlayerInfo(game.getCurrentPlayer().getPlayerName());
							gameStage.setButtonIcons();
							topCardButton.setIcon(new ImageIcon("picture/small/" + cardImage + ".png"));
							PopUp.this.dispose();
						}
					} catch (InvalidValueSubmissionException e) {
						// JOptionPane.showMessageDialog(null,"invalid card value submission error");
						System.err.println("invalid card value submission error");
					} catch (InvalidColorSubmissionException e) {
						// JOptionPane.showMessageDialog(null,"invalid card color submission error");
						System.err.println("invalid card color submission error");
					} catch (InvalidPlayerTurnException e) {
						System.err.println("invalid player turn error");
					}

				}
			}
		});
		p.add(btn);

		JButton btn2 = new JButton("取消");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				PopUp.this.dispose();
			}
		});
		p.add(btn2);

		JPanel p2 = new JPanel();
		add(p2, BorderLayout.CENTER);

		JLabel cardLabel = new JLabel();
		p2.add(cardLabel);

		cardImage = cardName;
		this.game = game;
		playerHandCard = game.getCurrentPlayer().getHandCard();
		choice = index;
		this.cardButtons = cardButtons;
		cardLabel.setIcon(new ImageIcon("picture/large/" + cardImage + ".png"));
		this.gameStage = gameStage;
		this.topCardButton = topCardButton;

	}

	public void setDeclaredColor(UnoCard.Color color) {
		this.declaredColor = color;
	}

}
