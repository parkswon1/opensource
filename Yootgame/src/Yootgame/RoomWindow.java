package Yootgame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.*;
import db.Database;
import java.awt.BorderLayout;

public class RoomWindow extends JFrame {
	private JPanel contentPane;
	
	String userId = "root";
	db.Database data = new db.Database();
	
	/**
	 * Create the frame.
	 */
	public RoomWindow() {
		new FirstPage();
        
	}
}