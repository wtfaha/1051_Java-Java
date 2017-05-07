package Start;

import java.awt.*;
import javax.swing.border.EmptyBorder;

import PlayerWalk.ReadMapFile;
import PlayerWalk.mainFrame;
import player.Player;

import javax.swing.*;
import java.awt.event.*;

public class GUIStart extends JFrame {
	private JPanel contentPane;
	private JTextField txtjave;
	private Input newInput;

	public GUIStart() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JButton button = new JButton("按我開始");
		button.setFont(new Font("標楷體", Font.PLAIN, 24));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newInput = new Input();
				setVisible(false);
				newInput.run();
			}
		});
		button.setForeground(Color.BLACK);
		button.setBackground(Color.GRAY);
		contentPane.add(button, BorderLayout.SOUTH);

		txtjave = new JTextField();
		txtjave.setHorizontalAlignment(SwingConstants.CENTER);
		txtjave.setFont(new Font("標楷體", Font.PLAIN, 40));
		txtjave.setForeground(Color.WHITE);
		txtjave.setBackground(Color.BLACK);
		txtjave.setText("Java人生");
		contentPane.add(txtjave, BorderLayout.CENTER);
		txtjave.setColumns(10);
		txtjave.setEditable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	public Player getPlayer() {
		return newInput.getPlayer();
	}
}
