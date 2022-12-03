package Yootgame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoginWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("Log-in");
        setSize(400,540);
        setDefaultCloseOperation(LoginWindow.DISPOSE_ON_CLOSE);
        setLocation(200, 0);
        setVisible(true);
	}
}
