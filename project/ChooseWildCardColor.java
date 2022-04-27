import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChooseWildCardColor extends JFrame {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	private Font font = new Font("Helvetica", Font.BOLD, 30);
	private UnoCard.Color wildColor = null;
	PopUp popUp;

	public ChooseWildCardColor() {
		super("check wild color");
		initComponent();
	}

	public ChooseWildCardColor(PopUp popUp) {
		super("check wild color");
		initComponent();
		this.popUp = popUp;
	}

	public UnoCard.Color choseColor(UnoCard card) {
		if (card.getColor() == UnoCard.Color.Wild) {
			this.setVisible(true);
		}
		return card.getColor();
	}

	public void initComponent() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setLayout(new BorderLayout());

		JLabel b1 = new JLabel("為你的萬能牌選個顏色");
		b1.setFont(font);
		add(b1, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));

		JButton btn = new JButton("紅色");
		btn.setBackground(Color.red);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				wildColor = UnoCard.Color.Red;
				JLabel message = new JLabel("下次卡牌顏色為紅色");
				ImageIcon icon = new ImageIcon("picture/small/change_red.png");
				JOptionPane.showMessageDialog(null, message);
				ChooseWildCardColor.this.dispose();
				popUp.setDeclaredColor(wildColor);
				popUp.gameStage.setPlayerInfo(popUp.game.getCurrentPlayer().getPlayerName());
				popUp.gameStage.setButtonIcons();
				// popUp.topCardButton.setIcon(popUp.game.getTopCardImage());
				popUp.topCardButton.setIcon(icon);
				popUp.game.setValidColor(wildColor);
				popUp.dispose();
			}
		});
		panel.add(btn);

		JButton btn2 = new JButton("藍色");
		btn2.setBackground(Color.blue);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				wildColor = UnoCard.Color.Blue;
				JLabel message = new JLabel("下次卡牌顏色為藍色");
				ImageIcon icon = new ImageIcon("picture/small/change_blue.png");
				JOptionPane.showMessageDialog(null, message);
				ChooseWildCardColor.this.dispose();
				popUp.setDeclaredColor(wildColor);
				popUp.gameStage.setPlayerInfo(popUp.game.getCurrentPlayer().getPlayerName());
				popUp.gameStage.setButtonIcons();
				// popUp.topCardButton.setIcon(popUp.game.getTopCardImage());
				popUp.topCardButton.setIcon(icon);
				popUp.game.setValidColor(wildColor);
				popUp.dispose();
			}
		});
		panel.add(btn2);

		JButton btn3 = new JButton("綠色");
		btn3.setBackground(Color.green);
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				wildColor = UnoCard.Color.Green;
				JLabel message = new JLabel("下次卡牌顏色為綠色");
				ImageIcon icon = new ImageIcon("picture/small/change_green.png");
				JOptionPane.showMessageDialog(null, message);
				ChooseWildCardColor.this.dispose();
				popUp.setDeclaredColor(wildColor);
				popUp.gameStage.setPlayerInfo(popUp.game.getCurrentPlayer().getPlayerName());
				popUp.gameStage.setButtonIcons();
				// popUp.topCardButton.setIcon(popUp.game.getTopCardImage());
				popUp.topCardButton.setIcon(icon);
				popUp.game.setValidColor(wildColor);
				popUp.dispose();
			}
		});
		panel.add(btn3);

		JButton btn4 = new JButton("黃色");
		btn4.setBackground(Color.yellow);
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				wildColor = UnoCard.Color.Yellow;
				JLabel message = new JLabel("下次卡牌顏色為黃色");
				ImageIcon icon = new ImageIcon("picture/small/change_yellow.png");
				JOptionPane.showMessageDialog(null, message);
				ChooseWildCardColor.this.dispose();
				popUp.setDeclaredColor(wildColor);
				popUp.gameStage.setPlayerInfo(popUp.game.getCurrentPlayer().getPlayerName());
				popUp.gameStage.setButtonIcons();
				// popUp.topCardButton.setIcon(popUp.game.getTopCardImage());
				popUp.topCardButton.setIcon(icon);
				popUp.game.setValidColor(wildColor);
				popUp.dispose();
			}
		});
		panel.add(btn4);

		add(panel);

	}

}
