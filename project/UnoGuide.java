import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UnoGuide extends JFrame implements ActionListener {
	public static final int WIDTH = 810;
	public static final int HEIGHT = 630;
	private static JPanel p1;
	CardLayout card;
	private JButton btn1, btn2, btn3, btn4;
	// public static Game gui;

	public UnoGuide() {
		super("uno_help");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		p1 = new JPanel();
		add(p1);

		card = new CardLayout();
		p1.setLayout(card);
		ImageIcon pic = new ImageIcon("./picture/help1.png");
		JLabel b = new JLabel(pic);
		p1.add(b);

		ImageIcon pic1 = new ImageIcon("picture/help2.png");
		JLabel b1 = new JLabel(pic1);
		p1.add(b1);

		ImageIcon pic2 = new ImageIcon("picture/help3.png");
		JLabel b2 = new JLabel(pic2);
		p1.add(b2);

		ImageIcon pic3 = new ImageIcon("picture/help4.png");
		JLabel b3 = new JLabel(pic3);
		p1.add(b3);

		ImageIcon pic4 = new ImageIcon("picture/help5.png");
		JLabel b4 = new JLabel(pic4);
		p1.add(b4);

		ImageIcon pic5 = new ImageIcon("picture/help6.png");
		JLabel b5 = new JLabel(pic5);
		p1.add(b5);

		ImageIcon pic6 = new ImageIcon("picture/help7.png");
		JLabel b6 = new JLabel(pic6);
		p1.add(b6);

		ImageIcon pic7 = new ImageIcon("picture/help8.png");
		JLabel b7 = new JLabel(pic7);
		p1.add(b7);

		ImageIcon pic8 = new ImageIcon("picture/help9.png");
		JLabel b8 = new JLabel(pic8);
		p1.add(b8);

		ImageIcon pic9 = new ImageIcon("picture/help10.png");
		JLabel b9 = new JLabel(pic9);
		p1.add(b9);

		ImageIcon pic10 = new ImageIcon("picture/help11.png");
		JLabel b10 = new JLabel(pic10);
		p1.add(b10);

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		add(p2);

		btn1 = new JButton("下一頁");
		btn1.addActionListener(this);
		p2.add(btn1);

		btn2 = new JButton("第一頁");
		btn2.addActionListener(this);
		p2.add(btn2);

		btn3 = new JButton("最後一頁");
		btn3.addActionListener(this);
		p2.add(btn3);

		btn4 = new JButton("回首頁");
		btn4.addActionListener(this);
		p2.add(btn4);

		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			card.next(p1);
		} else if (e.getSource() == btn2) {
			card.first(p1);
		} else if (e.getSource() == btn3) {
			card.last(p1);
		} else if (e.getSource() == btn4) {
			this.setVisible(false);
			UnoHome home = new UnoHome();
		}
	}
}
