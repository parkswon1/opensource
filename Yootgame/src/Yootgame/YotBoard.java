package Yootgame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YotBoard extends JFrame {
	private JPanel panelPan;
	JButton [][]panButton;
	private PlayGame play;
	private int windowSizeX=1000;
	private int windowSizeY=700;
	private int buttonSizeX = windowSizeX/20;
	private int buttonSizeY = buttonSizeX;
	JButton throwButton, newPiece;
	JButton []testButton = new JButton[7];
	JButton nowPlayer;
	JButton panClick;
	JButton []playerInfobtn;
	JLabel yotResult;
	JLabel boardMessage;
	JLabel []playerInfo;
	
	JLabel line;
	
	public YotBoard(PlayGame playObject) {
		play = playObject;
		Toolkit tools = Toolkit.getDefaultToolkit();
		Image mouseimg = tools.getImage("./img/cursor.png");
		
		Cursor newcursor = tools.createCustomCursor(mouseimg, new Point(0,0), "LOL");
		setCursor(newcursor);
		
		line = new JLabel(new ImageIcon("./img/line.png"));
		
		panelPan = new JPanel();
		panButton = new JButton [3][21];
		int xpos=buttonSizeX*7;
		int ypos=buttonSizeY*7;
		double buttonInterval=buttonSizeX*1.25;
   	 	panelPan.setLayout(null);
   	 	panelPan.setBackground(new Color(255,255,255));
   	 
		line.setSize(330,332);
		line.setLocation(52,50);

   	 	for(int i=1;i<21;i++)
   	 	{
   	 		if(i<6) {
   	 			ypos -= buttonInterval;
   	 		}
   	 		else if(i<11) {
   	 			xpos -= buttonInterval;
   	 		}
   	 		else if(i<16) {
   	 			ypos += buttonInterval;
   	 		}
   	 		else {
   	 			xpos += buttonInterval;
   	 		}
   	 	
			if(i==5 || i==10 || i==15){
				panButton[0][i] = new JButton(new ImageIcon("./img/bigcircle.jpg"));
			}
			else if(i==20) {
				panButton[0][i] = new JButton(new ImageIcon("./img/startcircle.jpg"));
			}
			else {
				panButton[0][i] = new JButton(new ImageIcon("./img/circle.jpg"));
			}
			panButton[0][i] = createBoardBtn(panButton[0][i],xpos,ypos,buttonSizeX,buttonSizeY,false);
			panelPan.add(panButton[0][i]);
			
			panButton[0][i].addActionListener(play);
   	 	}
		ypos =buttonSizeY-10;
		xpos =buttonSizeX*7-10;
		int p;
		for(p=0;p<6;p++)
		{
			
			if(p==3)
			{
				xpos -= buttonSizeX;
				ypos += buttonSizeY;
			}else {
				if(p==0)
				{
					xpos -= buttonSizeX;
					ypos += buttonSizeY;
				}
				else 
				{
					panButton[1][p] = new JButton(new ImageIcon("./img/circle.jpg"));
					panButton[1][p] = createBoardBtn(panButton[1][p],xpos,ypos,buttonSizeX,buttonSizeY,false);
					xpos -= buttonSizeX;
					ypos += buttonSizeY;
					panelPan.add(panButton[1][p]);
			   	 	panelPan.add(line);

					panButton[1][p].addActionListener(play);
				}
			}
		}
		xpos = buttonSizeX-10;
		ypos = buttonSizeY-10;
		for(p=0;p<6;p++)
		{
			if(p==0) {
				xpos += buttonSizeX;
				ypos += buttonSizeY;
			}
			else
			{
				if(p==3) {
					panButton[2][p] = new JButton(new ImageIcon("./img/bigcircle.jpg"));
					JButton arrow = new JButton(new ImageIcon("./img/leftup.jpg"));
					arrow = createBoardBtn(arrow,xpos-buttonSizeX,ypos,buttonSizeX/2,buttonSizeY/2,false);
					panelPan.add(arrow);

				}else{
					panButton[2][p] = new JButton(new ImageIcon("./img/circle.jpg"));
				}
				panButton[2][p] = createBoardBtn(panButton[2][p],xpos,ypos,buttonSizeX,buttonSizeY,false);
				xpos += buttonSizeX;
				ypos += buttonSizeY;
				panelPan.add(panButton[2][p]);
		   	 	panelPan.add(line);

				panButton[2][p].addActionListener(play);
			}
		}
		
		throwButton = new JButton("윷 던지기");
		throwButton = createBoardBtn(throwButton,buttonSizeX*9,buttonSizeY*10,buttonSizeX*3,buttonSizeY,true);
		throwButton.setBackground(new Color(255,255,0));
		panelPan.add(throwButton);
		throwButton.addActionListener(play);
		
		panClick = new JButton("윷 판을 클릭하세요");
		panClick = createBoardBtn(panClick,buttonSizeX*9,buttonSizeY*9,buttonSizeX*3,buttonSizeY,true);
		panClick.setBackground(new Color(153,204,255));
		panelPan.add(panClick);
		
		newPiece = new JButton("새로운 말 꺼내기");
		newPiece = createBoardBtn(newPiece,buttonSizeX*9,buttonSizeY*11,buttonSizeX*3,buttonSizeY,true);
		newPiece.setBackground(new Color(153,204,255));
		panelPan.add(newPiece);
		newPiece.addActionListener(play);
		
		nowPlayer = new JButton();
		nowPlayer = createBoardBtn(nowPlayer,buttonSizeX,buttonSizeY*10,buttonSizeX*4,buttonSizeY,false);
		panelPan.add(nowPlayer);
		
		yotResult = new JLabel();
		yotResult.setSize(300,70);
		yotResult.setLocation(250,500);
		panelPan.add(yotResult);
		
		boardMessage = new JLabel("P0 차례");
		boardMessage.setSize(300,50);
		boardMessage.setLocation(50,400);
		panelPan.add(boardMessage);
		
		playerInfo=new JLabel[4];
		playerInfobtn=new JButton[4];
		ImageIcon []img = new ImageIcon[4];
		img[0] = new ImageIcon("./img/red.jpg");
		img[1] = new ImageIcon("./img/blue.jpg");
		img[2] = new ImageIcon("./img/green.jpg");
		img[3] = new ImageIcon("./img/yellow.jpg");
		
		for(int i=0;i<play.getPlayerNum();i++)
		{
			playerInfobtn[i] = new JButton();
			playerInfobtn[i].setIcon(img[i]);
			playerInfobtn[i] = createBoardBtn(playerInfobtn[i],buttonSizeX*9,i*buttonSizeY,buttonSizeX,buttonSizeY,false);
			playerInfo[i] = new JLabel("X");
			playerInfo[i].setSize(500,50);
			playerInfo[i].setLocation(buttonSizeX*10,i*buttonSizeY);
			panelPan.add(playerInfobtn[i]);
			panelPan.add(playerInfo[i]);
		}
		String []s = new String[6];
		s[0]="백도";
		s[1]="모";
		s[2]="도";
		s[3]="개";
		s[4]="걸";
		s[5]="윷";
		for(int q=0;q<6;q++)
		{
			testButton[q] = new JButton(s[q]);
			testButton[q] = createBoardBtn(testButton[q],buttonSizeX+q*60,buttonSizeY*9,buttonSizeX+10,buttonSizeY+10,true);
			panelPan.add(testButton[q]);
			testButton[q].addActionListener(play);
		}
		
        this.add(panelPan);
        this.setTitle("Yot play");
        this.setVisible(true);
        this.setSize(windowSizeX, windowSizeY);
	}
	JButton createBoardBtn(JButton btn, int x, int y, int width, int depth, boolean tf)
	{
		btn.setSize(width, depth);
		btn.setBorderPainted(tf);
		btn.setContentAreaFilled(tf);
		btn.setLocation(x,y);
		return btn;
	}
	void refreashFrame()//changeplayer와 printyotresult 화면 갱신때 빈칸으로 만듬
	{
		nowPlayer.setIcon(null);
		yotResult.setText("");
	}
	void changePlayer(int i)
	{
		ImageIcon []img = new ImageIcon[4];
		img[0] = new ImageIcon("./img/red.jpg");
		img[1] = new ImageIcon("./img/blue.jpg");
		img[2] = new ImageIcon("./img/green.jpg");
		img[3] = new ImageIcon("./img/yellow.jpg");
		nowPlayer.setIcon(img[i]);
	}
	void printResult(int i)
	{
		String text;
		switch(i){
		case -1:
			text = "빽도";
			break;
		case 1:
			text ="도";
			break;
		case 2:
			text ="개";
			break;
		case 3:
			text ="걸";
			break;
		case 4:
			text ="윷";
			break;
		case 5:
			text ="모";
			break;
		default:
			text = "printyotresult ERROR";
			break;
		}
		yotResult.setText(text);
	}
	void printPiece(int player, int posx, int posy, int num)
	{

		if(posx==0 && posy==0)
		{
			posx=0;
			posy=20;
		}
		else if(posx==0 && posy==-1)
		{
			posx=0;
			posy=19;
		}
		else if(posx==1 && posy==3)
		{
			posx=2;
			posy=3;
		}
		else if(posy>20)
		{
			player = 9;//골인 지점으로 들어와서 표시 안해줘도됨
		}
		if(player == 0)
		{
			printBtn("red",posx,posy,num);
		}
		else if(player == 1)
		{
			printBtn("blue",posx,posy,num);
		}
		else if(player == 2)
		{
			printBtn("green",posx,posy,num);
		}
		else if(player == 3)
		{
			printBtn("yellow",posx,posy,num);
		}
		else if(player == 4)//흰색 판으로 다시 되돌림
		{
			printBtn("circle",posx,posy,0);
		}
		else
		{
			//System.out.println("printmal error");
		}
		
	}
	void printBtn(String color, int posx, int posy, int num)
	{
		String url="";
		if(num != 0) {
			if((posx==0 && posy==5) || posy==10 || posy == 15 || (posx==2 && posy==3)) {
				url = "./img/big"+color+num+".jpg";
			}
			else if(posy==20) {
				url = "./img/start"+color+num+".jpg";
			}
			else {
				url = "./img/"+color+num+".jpg";
			}
		}
		else {
			if((posx==0 && posy==5) || posy==10 || posy == 15 || (posx==2 && posy==3)) {
				url = "./img/bigcircle.jpg";
			}
			else if(posy==20) {
				url = "./img/startcircle.jpg";
			}
			else {
				url = "./img/circle.jpg";
			}
		}
		panButton[posx][posy].setIcon(new ImageIcon(url));
	}
	void message(String s)
	{
		boardMessage.setText(s);
	}
	void finishMessage(int winner)
	{
		boardMessage.setText("Player "+winner+ " is win");
	}
	void setplayerInfo(int i, String s)
	{
		playerInfo[i].setText(s);
	}
	void buttonColor(String s)
	{
		switch (s)
		{
		case "throwBtnOFF"://1
			throwButton.setBackground(new Color(153,204,255));//던지기 파랑
			break;
		case "throwBtnON"://2
			throwButton.setBackground(new Color(255,255,0));//던지기 노랑
			break;
		case "newPieceBtnON"://3
			newPiece.setBackground(new Color(255,255,0));//새로운 말 노랑
			break;
		case "newPieceBtnOFF"://4
			newPiece.setBackground(new Color(153,204,255));//새로운 말 파랑
			break;
		case "clickBoardON"://5
			panClick.setBackground(new Color(255,255,0));//판클릭 노랑
			break;
		case "clickBoardOFF"://6
			panClick.setBackground(new Color(153,204,255));//판클릭 파랑
			break;
		}
	}
}