package Yootgame;

public class PlayConfig {
	private int playerNum;
	private int pieceNum;
	public int getPlayerNum() {
		return playerNum;
	}
	public PlayConfig() {
		playerNum = 2;
		pieceNum = 2;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public int getPieceNum() {
		return pieceNum;
	}
	public void setPieceNum(int pieceNum) {
		this.pieceNum = pieceNum;
	}
	
}
