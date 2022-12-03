package Yootgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FirstPage extends JFrame{
	private static final int MAX_PLAYER = 3;
	private static final int MAX_PIECE = 4;
	private JPanel panel = new JPanel();
	private JRadioButton playerNumbtn[] = new JRadioButton[MAX_PLAYER];
	private JRadioButton pieceNumbtn[] = new JRadioButton[MAX_PIECE];
	private JButton start = new JButton("시작");
	private ButtonGroup playerNumcheck = new ButtonGroup();
	private ButtonGroup pieceNumcheck = new ButtonGroup();
	private PlayConfig playConfig = new PlayConfig();
	private PlayerAdapter playerAdapter = new PlayerAdapter(playConfig);
	private PieceAdapter pieceAdapter = new PieceAdapter(playConfig);
	private ActionListener listen = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		if(e.getSource()==start)
    		{
    			System.out.println(playConfig.getPlayerNum()+" , "+ playConfig.getPieceNum());
    			new PlayGame(playConfig.getPlayerNum(), playConfig.getPieceNum());
    		}
    	}
    };
    public FirstPage(){
        JLabel lb1, lb2;
        lb1 = new JLabel("사용자 수");
        panel.add(lb1);
        for(int i = 0; i < playerNumbtn.length; i++) {
        	playerNumbtn[i] = new JRadioButton();
        	playerNumbtn[i].setText(Integer.toString(i+2));
        	playerNumcheck.add(playerNumbtn[i]);
        	panel.add(playerNumbtn[i]);
        	playerNumbtn[i].addActionListener(playerAdapter);
        	
        }
        playerNumbtn[0].setSelected(true);
        
        lb2 = new JLabel("말 갯수");
        panel.add(lb2);
        for(int i = 0; i < pieceNumbtn.length; i++) {
        	pieceNumbtn[i] = new JRadioButton();
        	pieceNumbtn[i].setText(Integer.toString(i+2));
        	pieceNumcheck.add(pieceNumbtn[i]);
        	panel.add(pieceNumbtn[i]);
        	pieceNumbtn[i].addActionListener(pieceAdapter);
        }
        pieceNumbtn[0].setSelected(true);
        

        start.setSelected(false);
        panel.add(start);
        start.addActionListener(listen);
        
        this.add(panel);
        this.setTitle("First page");
        this.setVisible(true);
        this.setSize(600, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}