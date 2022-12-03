package Yootgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.TextField;

import java.util.*;
import db.Database;

public class MainWindow extends JFrame {
	LoginWindow logW;
	SignupWindow signW;
	RoomWindow roomW;
	
	db.Database data = new db.Database();
	String userId = "root";
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("STRAGETY YUT NORI");
		setBounds(100, 100, 900, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel accountPanel = new JPanel();
		accountPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel selectPanel = new JPanel();
		selectPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout(0, 0));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(accountPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
						.addComponent(selectPanel, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(selectPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(accountPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
						.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)))
		);
		
		
		/**
		 * account panel
		 */
		
		JButton btnQuit = new JButton("Quit");
		accountPanel.add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JButton btnSignUp = new JButton("Sign Up");
		accountPanel.add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(signW == null) {
					signW = new SignupWindow();
				}
				else {
					signW.dispose();
					signW = new SignupWindow();
				}
			}
		});
		
		JButton btnLogIn = new JButton("Log-in");
		accountPanel.add(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(logW == null) {
					logW = new LoginWindow();
				}
				else {
					logW.dispose();
					logW = new LoginWindow();
				}
			}
		});
		
		
		/**
		 * select panel
		 */

		JButton btnGame = new JButton("Game Start");
		selectPanel.add(btnGame);
		btnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (roomW == null) {
					data.gameOrder(userId, "create");
					roomW = new RoomWindow();
				}
				else {
					roomW.dispose();
					data.gameOrder(userId, "create");
					roomW = new RoomWindow();
				}
			}
		});
		
//		JButton btnRandom = new JButton("Random Room");
//		selectPanel.add(btnRandom);
//		btnRandom.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		
		
		/**
		 * game panel
		 */
		
		JList<String> gameList = new JList<String>(data.gameShow());
		gamePanel.add(gameList);
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
//				list 클릯 시 새로고침
				data.gameShow();
			}
		});
		
		JScrollPane gamescroll = new JScrollPane(gameList);
		gamePanel.add(gamescroll);
		
		
		/**
		 * chat panel
		 */
		
		JTextArea chatArea = new JTextArea();
		chatPanel.add(chatArea, BorderLayout.CENTER);
		chatArea.setLineWrap(true);
		
		JScrollPane chatscroll = new JScrollPane(chatArea);
		chatPanel.add(chatscroll);
		
		JTextField chatField = new JTextField();
		chatPanel.add(chatField, BorderLayout.SOUTH);
		chatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField chat = (JTextField)e.getSource();
				chatArea.append(">>" + chat.getText() + "\n\n");
				data.chatting(userId, chat.getText());
				chat.setText("");
			}
		});
		
		
		contentPane.setLayout(gl_contentPane);
	}
	
}
