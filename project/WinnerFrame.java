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
import javax.swing.SwingConstants;

public class WinnerFrame extends JFrame implements ActionListener {
	public static final int WIDTH = 620;
	public static final int HEIGHT = 600;
	private JButton btn1, btn2;
	private static JPanel panel;
	private static JFrame YNf;
	private static JPanel YNpanel;
	private GameStage gameStage;
	Font font = new Font("Helvetica", Font.BOLD, 40);

	public WinnerFrame(Player player, GameStage gameStage) {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("uno");
		setVisible(true);

		this.gameStage = gameStage;
		gameStage.dispose();
		JPanel Panel = new JPanel();
		add(Panel);

		ImageIcon pic = new ImageIcon("picture/Winning_interface.png");
		JLabel b = new JLabel(pic);
		b.setLayout(new GridLayout(2, 1));
		Panel.add(b);

		JPanel panelup = new JPanel();
		panelup.setLayout(new GridLayout(3, 1));
		panelup.setOpaque(false);
		b.add(panelup);

		JPanel panelupup = new JPanel();
		panelupup.setOpaque(false);
		panelup.add(panelupup);

		JPanel panelupcenter = new JPanel();
		panelupcenter.setOpaque(false);
		panelup.add(panelupcenter);

		JLabel b1 = new JLabel("label");
		b1.setFont(font);
		b1.setText(player.getPlayerName());
		panelupcenter.add(b1);

		JLabel b2 = new JLabel("Win!");
		b2.setFont(font);
		panelupcenter.add(b2);

		JPanel panelupdown = new JPanel();
		panelupdown.setOpaque(false);
		panelup.add(panelupdown);

		JPanel paneldown = new JPanel();
		paneldown.setLayout(new GridLayout(1, 2));
		paneldown.setOpaque(false);
		b.add(paneldown);

		ImageIcon pic2 = new ImageIcon("picture/again button.png");
		btn1 = new JButton(pic2);
		btn1.addActionListener(this);
		btn1.setBorder(null);
		btn1.setContentAreaFilled(false);
		paneldown.add(btn1);

		ImageIcon pic3 = new ImageIcon("picture/Exit button.png");
		btn2 = new JButton(pic3);
		btn2.addActionListener(this);
		btn2.setBorder(null);
		btn2.setContentAreaFilled(false);
		paneldown.add(btn2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {

			dispose();
			UnoHome g = new UnoHome();
		}
		if (e.getSource() == btn2) {
			YNf = new JFrame();
			YNpanel = new JPanel();
			YNpanel.setLayout(new BorderLayout());
			JLabel label = new JLabel("Are you sure you want to exit?", SwingConstants.CENTER);
			label.setFont(new Font(null, Font.PLAIN, 20));
			YNpanel.add(label, BorderLayout.NORTH);
			int ans = JOptionPane.showConfirmDialog(YNf, YNpanel, "NOTE", JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			if (ans == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			if (ans == JOptionPane.NO_OPTION) {
				YNf.dispose();
			}
		}
	}
}