package Start;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import PlayerWalk.ReadMapFile;
import PlayerWalk.mainFrame;
//import Show.*;
import player.Player;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.SwingConstants;
import RPG.RPG;

public class Input extends JFrame {

	private JPanel contentPane;
	private JTextField txtjava;
	private Player player;

	public void run() {
		setVisible(true);
		String Name = JOptionPane.showInputDialog("輸入您的角色名稱:");
		if (Name == null)
			Name = "臭肥宅";
		else if (Name.trim().equals(""))
			Name = "臭魯宅";
		JOptionPane.showMessageDialog(null, "您的名字是" + Name, "確認名字", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, "歡迎加入，" + Name, "您好", JOptionPane.PLAIN_MESSAGE);
		player = new Player(Name);
		//player.setLV(10);
		RPG.MainFrame.init(player);
		setVisible(false);
		// p.getBackPackage().addWeapon(new normalWeapon(21, 35));
		// Show.showPackage(p);

		// p.getBackPackage().addWeapon(new normalWeapon(99999, 35));
		// Show.showPackage(p);
		// System.exit(0);
	}

	/**
	 * Create the frame.
	 */
	public Input() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		txtjava = new JTextField();
		txtjava.setBackground(Color.BLACK);
		txtjava.setHorizontalAlignment(SwingConstants.CENTER);
		txtjava.setForeground(Color.WHITE);
		txtjava.setFont(new Font("標楷體", Font.PLAIN, 40));
		txtjava.setText("歡迎加入新的勇者加入");
		contentPane.add(txtjava, BorderLayout.CENTER);

		txtjava.setColumns(10);
		txtjava.setEditable(false);
		setLocationRelativeTo(null);
		// setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// dispose();
		// System.exit(0);
	}

	public Player getPlayer() {
		return player;
	}
}
