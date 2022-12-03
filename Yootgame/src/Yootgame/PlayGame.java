package Yootgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayGame implements ActionListener{
	private Player []players;
	private YotBoard board;
	private int pieceNum;
	private int playerNum;
	private int turn=0;
	private int winner=-1;
	private int result=0;
	private Player nowPlayer;
	private int control=1;
	
	PlayGame(int people, int mal)
	{
		players = new Player[people];
		for(int i=0;i<people;i++)
		{
			players[i] = new Player(i,mal);
		}
		playerNum = people;
		pieceNum = mal;
		board = new YotBoard(this);//yotboard에서 버튼이 클릭 되었다는 정보를 받기위해 본인 객체를 보냄
		
		for(int i=0; i<playerNum; i++) {
			board.setplayerInfo(i, players[i].playerPiece());
		}
	}
	
	public int getPlayerNum() {
		
		return playerNum;
	}
	
	int checkFinish()
	{
		for(int i=0;i<players.length;i++)
		{
			if(players[i].getPoint() == pieceNum)
			{
				players = null;
				board.finishMessage(i);
				return i;//i번째 플레이어 승리
			}
		}
		return -1;//게임 아직 안끝남
	}
	int checkCatch(int index)//지금 플레이어 인덱스값
	{
		Player catcher = players[index];//catcher는 지금의 플레이어
		int posx,posy;
		for(int q=0;q<catcher.getPieces().size();q++)//현재 플레이어의 모든 말
		{
			posx = catcher.getPieces().get(q).getX();
			posy = catcher.getPieces().get(q).getY();//현재 플레이어의 q번째 말 위치
			for(int i=0;i<players.length;i++)
			{
				if(i!=index)
				{
					if(players[i].checkCatch(posx,posy)==1)//i번째 Player의 말들과 비교해서 같으면 없앰
					{
						board.message("P"+index+"가 P"+i+"의 말을 잡았다");
						return 1;//한칸에 서로 다른 플레이어의 말이 겹쳐있지 않으므로 그냥 만나자 마자 종료
					}
				}
			}
		}
		return 0;
	}
	
	void boardMessage(String s) {
		board.message(s);
	}
	
	void boardRefreashFrame() {
		board.refreashFrame();
	}
	
	void phaze1ThrowYot()
	{
		if(control==1) {
			result =0;
			nowPlayer = players[turn];
			if(nowPlayer.getPieces().size()==0 && nowPlayer.getPieceNum()>0) {//판위에 말이 없고 대기중인 말이 있다면 0,0에 새로 만들고
				boardMessage("판 위에 올라가 있는 말 없음");
			}
			else
			{
				boardMessage("");
			}
			board.changePlayer(turn);
			result=Yoot.throwing();//던지기 버튼 클릭
			board.printResult(result);//던진 결과 화면에 출력
			phaze1changeBtncolor();//UI 버튼 색깔 변경
			
			for(int i=0; i<playerNum; i++) {
				board.setplayerInfo(i, players[i].playerPiece());
			}

			control = 3;
		}
	}
	
	void phaze2PutOnBoard()
	{
		if(control==2)
		{
			//말이 있으면 Player에서 알아서 찾고 도개걸 결과로 이동함
			if(nowPlayer.createPiece()==1) //대기중인 말의 수가 있다면 새로 생성 가능
			{
				nowPlayer.move(0, 0, result); //여기서 알아서 업어가는지 판단해줌
				
				for(int i=0; i<playerNum; i++) {
					board.setplayerInfo(i, players[i].playerPiece());
				}
				
				board.printPiece(turn,0,result,nowPlayer.getPieceUpdaNum(0,result));//플레이어, 이동 이후 좌표
				phaze2changeBtncolor();//UI 버튼 색깔 변경
				if(checkCatch(turn)==1 || result == 4 || result ==5)//다시 윷 던지기 조건
				{
					control=1;
					phaze1ThrowYot();
				}
				else
				{
					phaze4NextTurn();//지금 플레이어 턴 종료
				}
			}
			else
			{
				boardMessage("더 이상 말을 생성 할 수 없습니다.");
				control=3;//말 생성 한도를 넘어가면 phaze3Pieceact가 작동 할 수 있도록 한다.
			}
		}
	}
	
	void phaze3Pieceact(int posx, int posy)
	{
		int index;
		int x,y,point;
		
		if(control==3)
		{// if(지금 플레이어가 판 위에 올려 놓은 말의 갯수가 0 && 지금 플레이어의 남은 말 0 이상(남은 말=전체 말 - 골인한 말))
			if(nowPlayer.getPieces().size()==0 && nowPlayer.getPieceNum()>0)//판위에 말이 없고 대기중인 말이 있다면 0,0에 새로 만들고
			{
				control=2;
				phaze2PutOnBoard();
			}
			else
			{
				index = nowPlayer.checkEnable(posx, posy);//해당 버튼에 말이 있는지 확인 있으면 말 배열에 인덱스 반환
				if(index!=-1)
				{
					//말이 있으면 Player에서 알아서 찾고 도개걸 결과로 이동함
					board.printPiece(4, posx, posy, 0);//가기전에 흰색으로 원상 복구 후 이동
					if(nowPlayer.move(posx, posy, result)==1) //여기서 알아서 업어가는지 판단해줌
					{//들어가거나 겹쳐졌을때 화면에 표시를 안한다 이것때문에 자꾸 오류가 난다.
						boardMessage("P "+turn+" 말 하나가 업혔습니다");
						if(posx == 0 && posy == 5) {
							posx = 1;
							posy = 0;
						}
						else if(posx == 0 && posy==10){
							posx=2;
							posy=0;
						}
						posy=posy+result;
						if(result > 0) {
							if(posx == 1 && posy==3)
							{
								posx=2;
								posy=3;
							}
							else if(posx==1 && posy>5){
								posx=0;
								posy+=9;
							}
							else if(posx==2 && posy>5) {
								posx=0;
								posy+=14;
							}
						}else {
							if(posx==1 && posy<1) {
								posx=0;
								posy=5+posy;
							}else if(posx==1 && posy==3) {
								posx=2;
								posy=3;
							}else if(posx==2 && posy<1) {
								posx=0;
								posy=10+posy;
							}else if(posx==0 && posy<1) {
								posy=20+posy;
							}
						}
						index = nowPlayer.checkEnable(posx, posy);
						//이 지점에서 생기는 버그: 말 A가 이동해 말 B 위에 업혔다. 그럼 말 B point += 말 A point 하고 말 A 객체 삭제
						//말 A가 삭제되었으니 piece(==말)의 Arraylist의 index에 말이 없어서 범위 익셉션 뜸 그래서 index를 갱신해 줘야함
						x = nowPlayer.getPieces().get(index).getX();
						y = nowPlayer.getPieces().get(index).getY();
						point = nowPlayer.getPieces().get(index).getPoint();
						board.printPiece(turn,x,y,point);
					}
					else if(nowPlayer.checkPiecein() ==1)
					{
						boardMessage("P "+turn+" 말 하나가 골인했습니다");
					}
					else
					{
						x = nowPlayer.getPieces().get(index).getX();
						y = nowPlayer.getPieces().get(index).getY();
						point = nowPlayer.getPieces().get(index).getPoint();
						board.printPiece(turn,x,y,point);//플레이어, 이동 이후 좌표

					}

					for(int i=0; i<playerNum; i++) {
						board.setplayerInfo(i, players[i].playerPiece());
					}
					
					phaze2changeBtncolor();//UI 버튼 색깔 변경
					if(nowPlayer.getPieceNum()<=0 && nowPlayer.getPieces().size()<=0)//대기중인 말과 판위에 말이 없으면
					{
						control=-1;//경기 종료
						System.out.println("경기 종료");
						phaze4NextTurn();//해당 플레이어 턴 종료
					}
					else//게임이 끝나지 않았다면 (이렇게 해놔야 자바 익셉션 안뜸)
					{
						if(checkCatch(turn)==1 || result == 4 || result ==5)//다시 윷 던지기 조건
						{
							control=1;
							phaze1ThrowYot();
						}
						else
						{
							phaze4NextTurn();//해당 플레이어 턴 종료
						}
					}
				}
				else
				{
					boardMessage("엉뚱한 버튼 클릭함"+posx +" , "+posy);
				}
			}
		}
		else
		{
			//아직 윷을 던지지도 않았는데 판 클릭하면 아무 동작 안함
		}
	}

	void phaze4NextTurn()
	{
		winner = checkFinish();
		if(winner != -1)
		{
			control=5;//아무 동작 안함
			System.out.println(winner+ " 번째 플레이어가 승리하였습니다.");
		}
		else 
		{
			turn++;
			if(turn >= playerNum)
			{
				turn =0;
			}
			boardMessage("P "+turn+" 차례");
			boardRefreashFrame();//다음 플레이어로 넘어가니까 Piece 그림 바꿔줌
			initBtncolor();
			control = 1;
		}
	}
	
	void phaze1changeBtncolor() {
		board.buttonColor("throwBtnOFF");
		if(nowPlayer.getPieceNum()>0)//대기중인 말이 있다면 
		{
			board.buttonColor("newPieceBtnON");//새로운 말 버튼 활성화
		}
		if(nowPlayer.getPieces().size()>0)//판에 말이 있다면 
		{
			board.buttonColor("clickBoardON");//판 클릭 버튼 활성화
		}
	}
	
	void phaze2changeBtncolor() {
		if(nowPlayer.getPieces().size()>0)
		{
			board.buttonColor("clickBoardON");
		}
		else
		{
			board.buttonColor("clickBoardOFF");
		}
		if(nowPlayer.getPieceNum()>0)
		{
			board.buttonColor("newPieceBtnON");
		}
		else
		{
			board.buttonColor("newPieceBtnOFF");
		}
	}
	
	void initBtncolor() {
		board.buttonColor("throwBtnON");
		board.buttonColor("newPieceBtnOFF");
		board.buttonColor("clickBoardOFF");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==board.throwButton && control == 1)
		{
			phaze1ThrowYot();
		}
		if(e.getSource()==board.newPiece && control == 3)
		{
			control=2;
			phaze2PutOnBoard();
		}
		if(control==3) {
			for(int i=1;i<21;i++) {
				if(e.getSource()==board.panButton[0][i])
				{
					phaze3Pieceact(0,i);
				}
			}
			for(int p=1;p<6;p++) {
				if(e.getSource()==board.panButton[1][p])
				{
					phaze3Pieceact(1,p);
				}	
			}
			for(int q=1;q<6;q++) {
				if(e.getSource()==board.panButton[2][q])
				{
					phaze3Pieceact(2,q);
				}
			}
		}
		for(int r=0;r<6;r++)
		{
			if(e.getSource()==board.testButton[r] && control == 1)
			{
				phaze1ThrowYot();
				r--;
				if(r==0)
				{
					result = 5;
				}
				else
				{
					result = r;
				}
				board.printResult(result);//던진 결과 화면에 출력
			}
		}
	}
}
