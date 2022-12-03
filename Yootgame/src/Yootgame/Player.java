package Yootgame;

import java.util.ArrayList;

public class Player {
	private int id;
	private ArrayList<Piece> pieces;
	private int point;
	private int pieceNum;//아직 윷판에 안나오고 대기중인 말 수
	public Player(int id, int pieceNum){
		this.id = id;
		this.pieces = new ArrayList<Piece>();
		this.point =0;
		this.pieceNum = pieceNum;
	}
	
	public ArrayList<Piece> getPieces(){
		return this.pieces;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public int getPoint()
	{
		return this.point;
	}
	public int getPieceNum(){
		return this.pieceNum;
	}
	public int getPieceUpdaNum(int x, int y) {
		int a=0;
		if(x==0 && y==0)
		{
			x=0;
			y=20;
		}
		else if(x==0 && y==-1)
		{
			x=0;
			y=19;
		}
		else if(x==1 && y==3)
		{
			x=2;
			y=3;
		}
		else if(y>20)
		{
			return 0;
		}
		for(Piece piece : pieces)
		{
			if(piece.getX()==x && piece.getY()==y)
			{
				a = piece.getPoint();
				break;
			}
		}
		return a;
	}
	public int createPiece(){
		if(pieceNum>0)
		{
			pieces.add(new Piece());//판 위에 말 올림
			pieceNum--;
			System.out.println("말 생성 완료");
			return 1;
		}
		return 0;
	}
	public String playerPiece(){
		String s="";
		s = "<남은 말:"+pieceNum+" 포인트:"+point+">";
		return s;
	}
	public int move(int x, int y, int active) {  //(말 위치, 도개걸윷 정보를 보내주면) 무슨 말을 움직일까요?		
		if(pieces.size()!=0)
		{
			for(Piece piece : pieces)
			{
				if(piece.getX()==x && piece.getY()==y)//(말 위치에 있는 말을 찾고 도개걸윷 만큼 이동시킴)
				{
					piece.setPos(active);
				}
			}
			return checkUpda();
		}
		return 0;
	}
	public int checkCatch(int positionx, int positiony){
		for(Piece piece : pieces)
		{
			if(piece.getX()==positionx && piece.getY()==positiony)
			{
				System.out.println(piece.getPoint()+"개 잡혔다");
				pieceNum += piece.getPoint();
				pieces.remove(piece);
				return 1;
			}
		}
		return 0;
	}
	public int checkEnable(int posX, int posY) { //해당 위치에 말이 있나 없나
		int i=0;
		for(Piece piece : pieces){
			if(piece.getX()==posX && piece.getY()==posY){
				return i;
			}
			i++;
		}
		return -1;
	}
	public int checkPiecein(){
		System.out.println("P "+id+" 말 들어갔는지 확인");
		for(Piece piece : pieces){
			if(piece.getY()>20) {
				point += piece.getPoint();
				System.out.println("말이 "+piece.getPoint()+"개 들어옴 현재 포인트"+point);
				pieces.remove(piece);
				return 1;
			}
		}
		return 0;
	}
	public int checkUpda(){
		for(int p=0; p<pieces.size(); p++)
		{
			for(int q=p+1; q<pieces.size(); q++)
			{
				if(pieces.get(p).getX()==pieces.get(q).getX() && pieces.get(p).getY()==pieces.get(q).getY())//같은 위치에 말이 있다면
				{
					System.out.println("업힘 발생함");
					pieces.get(p).setPoint(pieces.get(q).getPoint());//q번째 말이 업은 만큼 p번째 말이 업음
					pieces.remove(q);
					return 1;
				}
			}
		}
		return 0;
	}
}