package Yootgame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SignupWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SignupWindow() {
		setTitle("Sign Up");
        setSize(400,540);
        setDefaultCloseOperation(SignupWindow.DISPOSE_ON_CLOSE);
        setLocation(200, 0);
        setVisible(true);
	}
}
