import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GameStage extends JFrame {
	public static final int WIDTH = 1210;
	public static final int HEIGHT = 700;

	private JButton unoButton, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14,
			topCardButton, drawCardButton;
	private static JPanel panel;
	private JLabel playerNameLabel, unoLabel;
	private AddPlayerName addPlayers;
	private Font font = new Font("Helvetica", Font.BOLD, 24);
	private String[] playerNames;
	Game game;
	private ArrayList<JButton> cardButton = new ArrayList<>();
	private ArrayList<String> cardName = new ArrayList<>();
	private PopUp window;

	public GameStage(String[] playerNames) {
		// initComponent
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("uno");
		setVisible(true);

		JPanel Panel = new JPanel();
		add(Panel);

		ImageIcon pic = new ImageIcon("picture/Game background2.png");
		JLabel b = new JLabel(pic);
		b.setLayout(new GridLayout(3, 1));
		Panel.add(b);

		JPanel panelup = new JPanel();
		// panelup.setLayout(new BorderLayout());
		panelup.setLayout(new GridLayout(1, 3));
		panelup.setOpaque(false);
		b.add(panelup);

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ButtonPanel.setOpaque(false);

		ImageIcon backImage = new ImageIcon("picture/back button.png");
		JButton backButton = new JButton(backImage); // 回首頁按鈕
		backButton.setBorder(null);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				GameStage.this.dispose();
				UnoHome home = new UnoHome();
			}
		});
		ButtonPanel.add(backButton);
		panelup.add(ButtonPanel);

		ImageIcon pic2 = new ImageIcon("picture\\small\\card_back_alt.png");
		btn14 = new JButton(pic2);
		btn14.setBorder(null);
		btn14.setContentAreaFilled(false);
		panelup.add(btn14);

		topCardButton = new JButton();
		topCardButton.setBorder(null);
		topCardButton.setOpaque(false);
		topCardButton.setContentAreaFilled(false);
		panelup.add(topCardButton);

		JPanel panelcenter = new JPanel();
		// panelcenter.setLayout(new BorderLayout());
		panelcenter.setLayout(new GridLayout(1, 4));
		panelcenter.setOpaque(false);
		b.add(panelcenter);

		JPanel infoPanel = new JPanel();
		// infoPanel.setLayout(new GridLayout(2,1));
		// Font infoFont = new Font("Helvetica", Font.BOLD, 20);
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setOpaque(false);
		playerNameLabel = new JLabel("player label");
		playerNameLabel.setFont(font);
		unoLabel = new JLabel("unoStatement");
		unoLabel.setFont(font);
		infoPanel.add(playerNameLabel, BorderLayout.CENTER);
		infoPanel.add(unoLabel, BorderLayout.SOUTH);
		panelcenter.add(infoPanel);

		JLabel b2 = new JLabel();
		panelcenter.add(b2);

		ImageIcon drawCardButtonIcon = new ImageIcon("picture/Draw card button.png");
		drawCardButton = new JButton(drawCardButtonIcon);
		drawCardButton.setBorder(null);
		drawCardButton.setContentAreaFilled(false);
		drawCardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JLabel message = new JLabel(game.getCurrentPlayer().getPlayerName() + " 抽一張牌");
				JOptionPane.showMessageDialog(GameStage.this, message);
				try {
					game.submitDraws(game.getCurrentPlayer());
				} catch (InvalidPlayerTurnException e) {
					System.err.println("invalid player turn error");
				}
				GameStage.this.setPlayerInfo(game.getCurrentPlayer().getPlayerName());
				GameStage.this.setButtonIcons();
				// GameStage.this.revalidate();
			}
		});
		panelcenter.add(drawCardButton);

		ImageIcon unoButtonIcon = new ImageIcon("picture/Uno button.png");
		unoButton = new JButton(unoButtonIcon);
		unoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				game.setPlayerOver();
				setPlayerInfo();
			}
		});
		unoButton.setBorder(null);
		unoButton.setContentAreaFilled(false);
		panelcenter.add(unoButton);

		JPanel paneldown = new JPanel();
		paneldown.setLayout(new GridLayout(1, 15));
		paneldown.setOpaque(false);
		b.add(paneldown);

		btn1 = new JButton();
		btn1.setOpaque(false);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(0);
			}
		});
		paneldown.add(btn1);

		btn2 = new JButton();
		btn2.setOpaque(false);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(1);
			}
		});
		paneldown.add(btn2);

		btn3 = new JButton();
		btn3.setOpaque(false);
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(2);
			}
		});
		paneldown.add(btn3);

		btn4 = new JButton();
		btn4.setOpaque(false);
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(3);
			}
		});
		paneldown.add(btn4);

		btn5 = new JButton();
		btn5.setOpaque(false);
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(4);
			}
		});
		paneldown.add(btn5);

		btn6 = new JButton();
		btn6.setOpaque(false);
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(5);
			}
		});
		paneldown.add(btn6);

		btn7 = new JButton();
		btn7.setOpaque(false);
		btn7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(6);
			}
		});
		paneldown.add(btn7);

		btn8 = new JButton();
		btn8.setOpaque(false);
		btn8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(7);
			}
		});
		paneldown.add(btn8);

		btn9 = new JButton();
		btn9.setOpaque(false);
		btn9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(8);
			}
		});
		paneldown.add(btn9);

		btn10 = new JButton();
		btn10.setOpaque(false);
		btn10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(9);
			}
		});
		paneldown.add(btn10);

		btn11 = new JButton();
		btn11.setOpaque(false);
		btn11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(10);
			}
		});
		paneldown.add(btn11);

		btn12 = new JButton();
		btn12.setOpaque(false);
		btn12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(11);
			}
		});
		paneldown.add(btn12);

		btn13 = new JButton();
		btn13.setOpaque(false);
		btn13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				useCard(12);
			}
		});
		paneldown.add(btn13);

		game = new Game(playerNames);
		populateArrayList();
		game.start(game, this);
		setPlayerInfo();
		topCardButton.setIcon(game.getTopCardImage());
		setButtonIcons();

	}

	public void populateArrayList() {
		cardButton.add(btn1);
		cardButton.add(btn2);
		cardButton.add(btn3);
		cardButton.add(btn4);
		cardButton.add(btn5);
		cardButton.add(btn6);
		cardButton.add(btn7);
		cardButton.add(btn8);
		cardButton.add(btn9);
		cardButton.add(btn10);
		cardButton.add(btn11);
		cardButton.add(btn12);
		cardButton.add(btn13);
	}

	public void setButtonIcons() {
		String iconList = game.getCurrentPlayer().getHandCard().stream().map(Object::toString)
				.collect(Collectors.joining(","));
		String[] cardList = iconList.split(",");
		cardName = new ArrayList<>(Arrays.asList(cardList));
		for (int i = 0; i < cardName.size(); i++) {
			ImageIcon imageIcon = new ImageIcon("picture/small/" + cardName.get(i) + ".png");
			cardButton.get(i).setIcon(imageIcon);
		}
		for (int i = cardName.size(); i < cardButton.size(); i++) {
			cardButton.get(i).setIcon(null);
		}
	}

	public void setPlayerInfo() {
		String currentPlayerName = game.getCurrentPlayer().getPlayerName();
		String unoStatement = game.getCurrentPlayer().getUnoStatement();
		playerNameLabel.setText("角色名稱: " + currentPlayerName);
		unoLabel.setText("Uno狀態: " + unoStatement);
	}

	public void setPlayerInfo(String currentPlayerName) {
		playerNameLabel.setText("角色名稱: " + currentPlayerName);
		String unoStatement = game.getCurrentPlayer().getUnoStatement();
		unoLabel.setText("Uno狀態: " + unoStatement);
	}

	public void useCard(int index) {
		try {
			if (cardName.get(index) != null) {
				String card = cardName.get(index);
				window = new PopUp(card, game, index, cardButton, this, topCardButton);
			}
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(this, "該位置沒有卡牌，請點選有卡牌位置!");
			
		}
		
	}

}
