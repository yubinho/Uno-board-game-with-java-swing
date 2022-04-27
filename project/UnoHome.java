import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UnoHome extends JFrame implements ActionListener {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 620;
	private JButton btn1, btn2, btn3;
	private static JFrame YNf;
	private static JPanel YNpanel;
	// public static Game_RedWin guiREDWin;
	// public static Game_BlackWin guiBLACKWin;
	// public static int round=1;

	public UnoHome() {
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("uno");
		this.setVisible(true);
		JPanel Panel = new JPanel();
		add(Panel);

		ImageIcon pic = new ImageIcon("picture/Start interface.png");
		JLabel b = new JLabel(pic);
		b.setLayout(new GridLayout(2, 1));
		Panel.add(b);

		JPanel panelup = new JPanel();
		panelup.setOpaque(false);
		b.add(panelup);

		JPanel panelDown = new JPanel();
		panelDown.setLayout(new GridLayout(4, 1));
		panelDown.setOpaque(false);
		b.add(panelDown);

		ImageIcon pic1 = new ImageIcon("picture/Start button.png");
		btn1 = new JButton(pic1);
		btn1.addActionListener(this);
		btn1.setOpaque(false);
		btn1.setBorder(null);
		btn1.setContentAreaFilled(false);
		panelDown.add(btn1);

		ImageIcon pic2 = new ImageIcon("picture/Rule description button.png");
		btn2 = new JButton(pic2);
		btn2.addActionListener(this);
		btn2.setOpaque(false);
		btn2.setBorder(null);
		btn2.setContentAreaFilled(false);
		panelDown.add(btn2);

		ImageIcon pic3 = new ImageIcon("picture/Exit button.png");
		btn3 = new JButton(pic3);
		btn3.addActionListener(this);
		btn3.setOpaque(false);
		btn3.setBorder(null);
		btn3.setContentAreaFilled(false);
		panelDown.add(btn3);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			this.dispose();
			AddPlayerName name = new AddPlayerName();
			// Game Wgame=new Game();

		} else if (e.getSource() == btn2) {
			this.setVisible(false);
			UnoGuide help = new UnoGuide();
			// help.setVisible(true);
		} else if (e.getSource() == btn3) {
			YNf = new JFrame();
			YNpanel = new JPanel();
			YNpanel.setLayout(new BorderLayout());
			JLabel label = new JLabel("確定要離開嗎?", SwingConstants.CENTER);
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

	public static void main(String[] args) {
		UnoHome gui = new UnoHome();
		gui.setVisible(true);
	}
}
