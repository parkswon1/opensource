package Yootgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class PieceAdapter implements ActionListener{
	PlayConfig playConfig;
	public PieceAdapter(PlayConfig playConfig) {
		this.playConfig = playConfig;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JRadioButton btn = (JRadioButton)e.getSource();
		
		int pieceNum = Integer.parseInt(btn.getText());
		playConfig.setPieceNum(pieceNum);
		System.out.println(playConfig.getPieceNum());
	}

}
