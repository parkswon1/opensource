package Yootgame;

public class Piece {

	private int x,y;
	private int point;
	
	public Piece(){
		this.x=0;
		this.y=0;
		this.point =1;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setPos(int act){
		fixpos_1(act);//두두번번  확확인인해해야야  버버그그  없없어어짐짐 대각선으로 타고 들어갈때 좌표 바꿔주기
		y += act;
		fixpos_2(act);//두두번번  확확인인해해야야  버버그그  없없어어짐짐 대각선 내부에서 일어나는 좌표 바꿔주기
	}
	public void setPoint(int a){
		point += a;//업었을때 업힌거 갯수
	}
	public void fixpos_1(int act){
		if(x==0 && y==5)//이렇게 설정해 놓고 출발
		{
			x=1;
			y=0;
		}
		else if(x==0 && y==10)
		{
			x=2;
			y=0;
		}
	}
	public void fixpos_2(int act){
		if(act>0){
			if(x==1 && y==3){
				x=2;
				y=3;
			}else if(x==1 && y>5){
				x=0;
				y+=9;
			}else if(x==2 && y>5){
				x=0;
				y+=14;
			}
		}
		else//백도
		{
			if(x==1 && y<1)
			{
				x=0;
				y=5+y;
			}
			else if(x==1 && y==3)//5.10 bug fix
			{
				x=2;
				y=3;
			}
			else if(x==2 && y<1)
			{
				x=0;
				y=10+y;
			}
			else if(x==0 && y<1)
			{
				y=20+y;
			}
		}
	}
	
}
