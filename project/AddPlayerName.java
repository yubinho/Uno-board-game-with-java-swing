import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddPlayerName extends JFrame {
	public static final int WIDTH = 620;
	public static final int HEIGHT = 600;
	private JButton saveButton, startButton;
	public ArrayList<String> playerList = new ArrayList<>();
	private Font font = new Font("Helvetica", Font.BOLD, 24);

	public AddPlayerName() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// setResizable(false);
		setTitle("uno");
		setVisible(true);

		JPanel Panel = new JPanel();
		add(Panel);

		ImageIcon pic = new ImageIcon("picture/addplayername.png"); // 背景
		JLabel b = new JLabel(pic);
		b.setLayout(new GridLayout(2, 1));
		Panel.add(b);

		JPanel panelup = new JPanel();
		panelup.setLayout(new GridLayout(2, 1));
		panelup.setOpaque(false);
		b.add(panelup);

		JPanel panelupup = new JPanel();
		panelupup.setOpaque(false);
		panelup.add(panelupup);

		JPanel panelupdown = new JPanel(); // 第二層
		// panelupdown.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelupdown.setOpaque(false);
		panelup.add(panelupdown);

		JLabel b1 = new JLabel("<-----角色名稱----->", SwingConstants.CENTER); // 玩家名稱提示
		panelupdown.add(b1);

		JTextField tf = new JTextField(50); // 輸入玩家名稱
		panelupdown.add(tf);

		JPanel panelDown = new JPanel(); // 第三層
		// panelDown.setLayout(new BorderLayout());
		panelDown.setLayout(new GridLayout(3, 1));
		panelDown.setOpaque(false);
		b.add(panelDown);
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(2, 4));
		playerPanel.setOpaque(false);
		panelDown.add(playerPanel, BorderLayout.NORTH);

		JLabel player1 = new JLabel("玩家1:名稱", SwingConstants.CENTER);
		player1.setFont(font);
		playerPanel.add(player1);

		JLabel player2 = new JLabel("玩家2:名稱", SwingConstants.CENTER);
		player2.setFont(font);
		playerPanel.add(player2);
		JLabel player3 = new JLabel("玩家3:名稱", SwingConstants.CENTER);
		player3.setFont(font);
		playerPanel.add(player3);
		JLabel player4 = new JLabel("玩家4:名稱", SwingConstants.CENTER);
		player4.setFont(font);
		playerPanel.add(player4);

		ImageIcon pic1 = new ImageIcon("picture/Save button.png");
		saveButton = new JButton(pic1); // 保存按鈕
		saveButton.setOpaque(false);
		saveButton.setBorder(null);
		saveButton.setContentAreaFilled(false);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (tf.getText().isEmpty()) {
					JLabel message = new JLabel("請輸入玩家名稱");
					JOptionPane.showMessageDialog(null, message);
				} else {
					String playerName = tf.getText().trim();
					playerList.add(playerName);

					if (playerList.size() == 1) {
						player1.setText("玩家1: " + playerList.get(0));
					} else if (playerList.size() == 2) {
						player1.setText("玩家1: " + playerList.get(0));
						player2.setText("玩家2: " + playerList.get(1));
					} else if (playerList.size() == 3) {
						player1.setText("玩家1: " + playerList.get(0));
						player2.setText("玩家2: " + playerList.get(1));
						player3.setText("玩家3: " + playerList.get(2));
					} else if (playerList.size() == 4) {
						player1.setText("玩家1: " + playerList.get(0));
						player2.setText("玩家2: " + playerList.get(1));
						player3.setText("玩家3: " + playerList.get(2));
						player4.setText("玩家4: " + playerList.get(3));
					}

					if (playerList.size() == 5) {
						playerList.remove(playerName);
						JLabel message = new JLabel("此遊戲只允許2-4人");
						JOptionPane.showMessageDialog(AddPlayerName.this, message);
						tf.setText("");
					} else if (playerList.size() > 0 || playerList.size() < 3) {
						JLabel message = new JLabel("保存成功");
						JOptionPane.showMessageDialog(AddPlayerName.this, message);
						tf.setText("");
					}

				}

			}
		});
		// panelDown.add(saveButton, BorderLayout.WEST);

		ImageIcon pic2 = new ImageIcon("picture/Enter button.png");
		startButton = new JButton(pic2); // 開始按鈕
		startButton.setOpaque(false);
		startButton.setBorder(null);
		startButton.setContentAreaFilled(false);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (playerList.size() <= 1) {
					JLabel message = new JLabel("此遊戲至少需2人以上");
					JOptionPane.showMessageDialog(AddPlayerName.this, message);
				} else {
					AddPlayerName.this.dispose();
					GameStage gameStage = new GameStage(getPlayerArray());
					// Winning_interface Winning_interface = new Winning_interface();
				}
			}
		});
		ImageIcon backImage = new ImageIcon("picture/back button.png");
		JButton backButton = new JButton(backImage);
		backButton.setBorder(null);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				AddPlayerName.this.dispose();
				UnoHome home = new UnoHome();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.add(saveButton);
		buttonPanel.add(startButton);
		buttonPanel.add(backButton);
		// panelDown.add(btn2, BorderLayout.EAST);
		panelDown.add(buttonPanel, BorderLayout.SOUTH);
		JPanel paneldowndown = new JPanel();
		paneldowndown.setOpaque(false);
		panelDown.add(paneldowndown);

	}

	public String[] getPlayerArray() {
		return playerList.toArray(new String[playerList.size()]);
	}

}
