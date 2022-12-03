package Yootgame;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class PlayerTest{
	
	PlayGame playgame = new PlayGame(2, 4); // Player 2명에 Player 각각의 말이 4개인 게임 진행 Test
	Player player0 = new Player(0,4); // id 값이 0(-> P0), 말의 개수가 4개인 Player0 객체 생성
	Player player1 = new Player(1,4); // id 값이 1(-> P1), 말의 개수가 4개인 Player1 객체 생성
	
	@Test
	public void playerInfoTest() { // Player의 id값과, 말의 개수가 올바른지 확인
		
		//player0 info test
		
		assertEquals(0, player0.getId());
		assertEquals(4, player0.getPieceNum());
		
		//player1 info test
		
		assertEquals(1, player1.getId());
		assertEquals(4, player1.getPieceNum());
	}
	
	@Test
	public void pieceMoveCaseOne() { // Player0의 말 1개로 윷놀이 판을 규칙에 맞게 잘 움직이는지 확인(Case 1)
		
		// 윷놀이 판의 위치 정보가 정확히 어떤 방법으로 이뤄졌는지는 관련 Document 참고 바람
		
		player0.createPiece(); // player0의 첫 번째 말, 판 위에 올리기
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, 초기 X 좌표 0인지 확인 
		assertEquals(0, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, 초기 Y 좌표 0인지 확인
		
		player0.getPieces().get(0).setPos(2); // 첫 번째 말, '개'만큼 이동(2칸 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=0) -> (X=0, Y=2)로 바뀜
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(2, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 2인지 확인
		
		player0.getPieces().get(0).setPos(3); // 첫 번째 말, '걸'만큼 이동(3칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=2) -> (X=0, Y=5)로 바뀜

		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(5, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 5인지 확인
		
		// (X=0, Y=5)인 구간에서 말을 움직일 때, 오른쪽 상단에서 시작하는 대각선으로 잘 들어가는지 확인(이때 X의 좌표가 1로 바뀜)
		// (X=0, Y=5) = (X=1, Y=0)
		// 따라서, '도'를 적용하면 (X=1, Y=1)이라는 결과가 나와야 함
		
		player0.getPieces().get(0).setPos(1); // 첫 번째 말, '도'만큼 이동(1칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=5) = (X=1, Y=0) -> (X=1, Y=1)
		
		assertEquals(1, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 1인지 확인
		assertEquals(1, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 1인지 확인
		
		// 윷놀이 판의 정 가운데 위치에 있을 때 좌표가 X=2, Y=3인 구간으로 바뀌는지 확인
		// 기존에 (X=1, Y=1) 위치에서 '개'를 적용하면 (X=1, Y=3)으로 바뀜
		// (X=1, Y=3) = (X=2, Y=3)
		
		player0.getPieces().get(0).setPos(2); // 첫 번째 말, '개'만큼 이동(2칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=1, Y=1) -> (X=1, Y=3) = (X=2, Y=3)
		
		assertEquals(2, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 2인지 확인
		assertEquals(3, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 3인지 확인
		
		// 기존에 (X=2, Y=3) 위치에서 '걸'을 적용하면 (X=2, Y=6)으로 바뀜(결승점)
		// (X=2, Y=6) = (X=0, Y=20)
		
		player0.getPieces().get(0).setPos(3); // 첫 번째 말, '걸'만큼 이동(3칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=2, Y=3) -> (X=2, Y=6) = (X=0, Y=20) = 결승점
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(20, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 20인지 확인
		
		// 기존에 (X=0, Y=20) 위치에서 '개'를 적용하면 (X=0, Y=22)(Y가 20보다 크면, 결승점 통과)
		
		player0.getPieces().get(0).setPos(2); // 첫 번째 말, '개'만큼 이동(2칸 추가로 이동)
		
		// 결승점에서 '개'를 적용한 것이므로 말은 윷놀이 판을 다 돌은 말로 처리
		
		assertEquals(1, player0.checkPiecein()); // player0의 말 중 윷놀이 판을 다 돌고 들어온 말이 있는지 확인
		
		assertEquals(3, player0.getPieceNum()); // player0의 남은 말이 3개인지 확인
	}
	
	@Test
	public void pieceMoveCaseTwo() { // Player1의 말 1개로 윷놀이 판을 규칙에 맞게 잘 움직이는지 확인(Case 2)
		
		// 윷놀이 판의 위치 정보가 정확히 어떤 방법으로 이뤄졌는지는 관련 Document 참고 바람
		
		player1.createPiece(); // player1의 첫 번째 말, 판 위에 올리기
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, 초기 X 좌표 0인지 확인 
		assertEquals(0, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, 초기 Y 좌표 0인지 확인
		
		player1.getPieces().get(0).setPos(2); // 첫 번째 말, '개'만큼 이동(2칸 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=0) -> (X=0, Y=2)로 바뀜
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(2, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, Y 좌표 2인지 확인
		
		player1.getPieces().get(0).setPos(5); // 첫 번째 말, '모'만큼 이동(5칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=2) -> (X=0, Y=7)로 바뀜

		assertEquals(0, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(7, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, Y 좌표 7인지 확인
		
		player1.getPieces().get(0).setPos(3); // 첫 번째 말, '걸'만큼 이동(3칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=7) -> (X=0, Y=10)으로 바뀜
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(10, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, Y 좌표 10인지 확인
		
		// (X=0, Y=10)인 구간에서 말을 움직일 때, 왼쪽 상단에서 시작하는 대각선으로 잘 들어가는지 확인(이때 X의 좌표가 2로 바뀜)
		// (X=0, Y=10) = (X=2, Y=0)
		// 따라서, '도'를 적용하면 (X=2, Y=1)이라는 결과가 나와야 함
		
		player1.getPieces().get(0).setPos(1); // 첫 번째 말, '도'만큼 이동(1칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=10) = (X=2, Y=0) -> (X=2, Y=1)
		
		assertEquals(2, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, X 좌표 2인지 확인
		assertEquals(1, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, Y 좌표 1인지 확인
		
		player1.getPieces().get(0).setPos(2); // 첫 번째 말, '개'만큼 이동(2칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=2, Y=1) -> (X=2, Y=3)
		
		assertEquals(2, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, X 좌표 2인지 확인
		assertEquals(3, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, Y 좌표 3인지 확인
		
		player1.getPieces().get(0).setPos(4); // 첫 번째 말, '윷'만큼 이동(4칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=2, Y=3) -> (X=2, Y=7) = (X=0, Y=21) = 결승점 통과
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(21, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, Y 좌표 21인지 확인
		
		assertEquals(1, player1.checkPiecein()); // player1의 말 중 윷놀이 판을 다 돌고 들어온 말이 있는지 확인
		
		assertEquals(3, player1.getPieceNum()); // player1의 남은 말이 3개인지 확인
	}
	
	@Test
	public void pieceMoveCaseThree() { // Player0의 말 1개로 윷놀이 판을 규칙에 맞게 잘 움직이는지 확인(Case 3)
		
		// 윷놀이 판의 위치 정보가 정확히 어떤 방법으로 이뤄졌는지는 관련 Document 참고 바람
		
		player0.createPiece(); // player0의 첫 번째 말, 판 위에 올리기
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, 초기 X 좌표 0인지 확인 
		assertEquals(0, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, 초기 Y 좌표 0인지 확인
		
		player0.getPieces().get(0).setPos(3); // 첫 번째 말, '걸'만큼 이동(3칸 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=0) -> (X=0, Y=3)로 바뀜
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(3, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 3인지 확인
		
		player0.getPieces().get(0).setPos(3); // 첫 번째 말, '걸'만큼 이동(3칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=3) -> (X=0, Y=6)으로 바뀜

		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(6, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 6인지 확인
		
		player0.getPieces().get(0).setPos(2); // 첫 번째 말, '개'만큼 이동(2칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=6) -> (X=0, Y=8)으로 바뀜

		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(8, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 8인지 확인
		
		player0.getPieces().get(0).setPos(5); // 첫 번째 말, '모'만큼 이동(5칸 추가로 이동)
		player0.getPieces().get(0).setPos(3); // 첫 번째 말, '걸'만큼 이동(3칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=8) -> (X=0, Y=16)으로 바뀜

		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(16, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 16인지 확인
		
		player0.getPieces().get(0).setPos(5); // 첫 번째 말, '모'만큼 이동(5칸 추가로 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=16) -> (X=0, Y=21)으로 바뀜
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(21, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 21인지 확인
		
		assertEquals(1, player0.checkPiecein()); // player0의 말 중 윷놀이 판을 다 돌고 들어온 말이 있는지 확인
		
		assertEquals(3, player0.getPieceNum()); // player0의 남은 말이 3개인지 확인
	}
	
	@Test
	public void pieceGroupTest() {
		
		player0.createPiece(); // player0의 첫 번째 말, 판 위에 올리기
		
		player0.getPieces().get(0).setPos(3); // 첫 번째 말, '걸'만큼 이동(3칸 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=0) -> (X=0, Y=3)로 바뀜
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(3, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 3인지 확인
		
		player0.createPiece(); // player0의 두 번째 말, 판 위에 올리기
		
		player0.getPieces().get(1).setPos(2); // 두 번째 말, '개'만큼 이동(2칸 이동)

		// 그러면 말의 좌표는 (X=0, Y=0) -> (X=0, Y=2)로 바뀜
		
		assertEquals(0, player0.getPieces().get(1).getX()); // player0의 두 번째 말, X 좌표 0인지 확인
		assertEquals(2, player0.getPieces().get(1).getY()); // player0의 두 번째 말, Y 좌표 2인지 확인
		
		assertEquals(0, player0.checkUpda()); // player0의 첫 번째 말, 두 번째 말이 업히지 않은 상태기 때문에 0 리턴
		
		player0.getPieces().get(1).setPos(1); // 두 번째 말, '도'만큼 이동(1칸 추가 이동)

		// 그러면 말의 좌표는 (X=0, Y=2) -> (X=0, Y=3)로 바뀜
		
		assertEquals(0, player0.getPieces().get(1).getX()); // player0의 두 번째 말, X 좌표 0인지 확인
		assertEquals(3, player0.getPieces().get(1).getY()); // player0의 두 번째 말, Y 좌표 3인지 확인
		
		// 그러면 현재 player0의 첫 번재 말과 두 번째 말이 모두 (X=0, Y=3)에 위치함, 잘 업혔는지 체크
		
		assertEquals(1, player0.checkUpda()); // player0의 첫 번째 말, 두 번째 말 잘 업혔기 때문에 1 리턴
		
		assertEquals(2, player0.getPieces().get(0).getPoint()); // 업힌 말의 개수는 총 2개, 업힌 말의 개수를 리턴해주는 getPoint() 값이 2인지 확인
		
		player0.createPiece(); // player0의 세 번째 말, 판 위에 올리기
		
		player0.getPieces().get(1).setPos(3); // 세 번째 말, '걸'만큼 이동(3칸 이동)
		
		assertEquals(1, player0.checkUpda()); // player0의 첫 번째 말, 두 번째 말, 세 번째 말 모두 잘 업혔기 때문에 1 리턴
		
		assertEquals(3, player0.getPieces().get(0).getPoint()); // 업힌 말의 개수는 총 3개, 업힌 말의 개수를 리턴해주는 getPoint() 값이 3인지 확인
	}
	
	@Test
	public void pieceCatchTest() {
		
		player0.createPiece(); // player0의 첫 번째 말, 판 위에 올리기
		
		player0.getPieces().get(0).setPos(4); // player0의 첫 번째 말, '윷'만큼 이동(4칸 이동)
		
		// 그러면 말의 좌표는 (X=0, Y=0) -> (X=0, Y=4)로 바뀜
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(4, player0.getPieces().get(0).getY()); // player0의 첫 번째 말, Y 좌표 4인지 확인
		
		assertEquals(1, player0.getPieces().size()); // 윷놀이 판에 나와 있는 player1의 말이 1개인지 확인
		
		player1.createPiece(); // player1의 첫 번째 말, 판 위에 올리기
		
		player1.getPieces().get(0).setPos(3); // player1의 첫 번째 말, '걸'만큼 이동(3칸 이동)
		
		// 그러면 player1의 첫 번째 말의 좌표는 (X=0, Y=0) -> (X=0, Y=3)으로 바뀜
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(3, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, Y 좌표 3인지 확인
		
		player1.getPieces().get(0).setPos(1); // player1의 첫 번째 말, '도'만큼 이동(1칸 이동)
		
		// 그러면 player1의 첫 번째 말의 좌표는 (X=0, Y=3) -> (X=0, Y=4)로 바뀜
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1의 첫 번째 말, X 좌표 0인지 확인
		assertEquals(4, player1.getPieces().get(0).getY()); // player1의 첫 번째 말, Y 좌표 4인지 확인
		
		assertEquals(1, player0.checkCatch(player1.getPieces().get(0).getX(), player1.getPieces().get(0).getY())); // player0의 말이 player1의 말에 의해 잡혔는지 확인
		
		assertEquals(0, player0.getPieces().size()); // 그러면 윷놀이 판에 나와 있는 player0의 말은 0개가 되는지 확인
		
		// 이제는 player0의 말들이 업힌 상태에서, player1의 말에 의해 잡혔을 때 잘 작동하는지 확인
		
		player0.createPiece(); // player0의 첫 번째 말, 판 위에 올리기
		player0.move(0, 0, 3); // player0의 첫 번째 말, '걸'만큼 이동(3칸 이동)

		player0.createPiece(); // player0의 두 번째 말, 판 위에 올리기
		player0.move(0, 0, 3); // player0의 두 번째 말, '걸'만큼 이동(3칸 이동)

		player0.createPiece(); // player0의 세 번째 말, 판 위에 올리기
		player0.move(0, 0, 3); // player0의 세 번째 말, '걸'만큼 이동(3칸 이동)
		
		player1.createPiece(); // player1의 두 번째 말, 판 위에 올리기
		
		player1.getPieces().get(1).setPos(3); // player1의 두 번째 말, '걸'만큼 이동(3칸 이동)
		
		playgame.checkCatch(1);
		assertEquals(1, player0.checkCatch(player1.getPieces().get(1).getX(), player1.getPieces().get(1).getY())); // player0의 말 3개가 player1의 두 번째 말에 의해 잡혔는지 확인
		
		assertEquals(0, player0.getPieces().size()); // 윷놀이 판에 나와 있는 player0의 말이 0개인지 확인
	}
	
	@Test
	public void playerPointTest() {
		
		player0.createPiece(); // player0의 첫 번째 말, 판 위에 올리기
		player0.getPieces().get(0).setPos(22); // player0의 첫 번째 말, 22칸 이동(결승점 통과)

		assertEquals(1, player0.checkPiecein()); // 결승점을 통과한 말이 있기 때문에 1 리턴
		assertEquals(1, player0.getPoint()); // 결승점을 통과한 말이 1개이기 때문에 player0의 포인트는 1점
		
		player0.createPiece(); // player0의 두 번째 말, 판 위에 올리기
		player0.getPieces().get(0).setPos(21); // player0의 두 번째 말, 21칸 이동(결승점 통과)
		
		assertEquals(1, player0.checkPiecein()); // 결승점을 통과한 말이 있기 때문에 1 리턴
		assertEquals(2, player0.getPoint()); // 결승점을 통과한 말이 총 2개이기 때문에 player0의 포인트는 2점
		
		// 아래는 나머지 두 개 말을 업어서 결승점을 통과한 경우
		
		player0.createPiece(); // player0의 세 번째 말, 판 위에 올리기
		player0.getPieces().get(0).setPos(3); // player0의 세 번째 말, '걸'만큼 이동(3칸 이동)
		
		player0.createPiece(); // player0의 네 번째 말, 판 위에 올리기
		player0.getPieces().get(1).setPos(3); // player0의 네 번째 말, '걸'만큼 이동(3칸 이동)
		
		assertEquals(1, player0.checkUpda()); // player0의 세 번째 말, 네 번째 말 모두 잘 업혔기 때문에 1 리턴

		player0.getPieces().get(0).setPos(19); // player0의 세 번째, 네 번째 말, 19칸 추가 이동(결승점 통과)
		
		assertEquals(1, player0.checkPiecein()); // 결승점을 통과한 말이 있기 때문에 1 리턴
		assertEquals(4, player0.getPoint()); // 결승점을 통과한 말이 총 4개이기 때문에 player0의 포인트는 4점
	}
	
	@After
	public void teardown() {
		System.out.println("teardown!");
	}
}
