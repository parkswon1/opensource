package Yootgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class PlayerAdapter implements ActionListener{

	PlayConfig playConfig;
	public PlayerAdapter(PlayConfig playConfig) {
		this.playConfig = playConfig;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JRadioButton btn = (JRadioButton)e.getSource();
		int playerNum = Integer.parseInt(btn.getText());
		playConfig.setPlayerNum(playerNum); 
		System.out.println(playConfig.getPlayerNum());
	}

}
