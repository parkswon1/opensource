package Yootgame;

import java.util.Random;

// 윷은 윷놀이 게임에서 인스턴스를 만들 필요없이 클래스 자체로 한번만 만들어지면 되지 때문에 모든 변수를 static으로 만들어준다.
public class Yoot {
	
	// 윷을 던져서 나온 값에 해당하는 플래그
	public static final int BACKDO = -1;	// 빽도: 1칸 뒤로
	public static final int DO = 1;			// 도: 1칸 전진
	public static final int GAE = 2;		// 개: 2칸 전진
	public static final int GUL = 3;		// 걸: 3칸 전진
	public static final int YOOT = 4;		// 윷: 4칸 전진
	public static final int MO = 5;			// 모: 5칸 전진
	
	public static final int SIDE_FLAT = 1;	// 평평한 면을 나타내는 플래그 변수
	public static final int SIDE_ROUND = 0;	// 둥근 면을 나타내는 플래그 변수

	private static final float PROB_FLAT = 0.6f;	// 평평한 면이 나올 확률, 대체로  평평한 면이 나올 확률이 60%, 둥근 면이 나올 확률을 40%로 한다.
	
	private static int[] yootSticks = new int[4];	// 윷에 사용되는 윷가락 4개를 배열로 선언한다. 그중 첫번째에 해당되는 윷은 빽도에 해당하는 윷가락이다.
	
	
	
	// 윷을 랜덤으로 던지는 메소드
	public static int throwing() {
		Random random = new Random();	// 윷이 랜덤하게 던져질 수 있도록 랜덤값을 생성한다.
		int sum = 0;					// 경우의 수를 확인해서 윷의 값을 정해주기 위해서 나온 값의 총합을 저장할 변수
		
		// 총 4번(윷가락의 개수) 반복하면서 각각의 윷가락에 해당되는 면을 랜덤하게 바꿔주고 모든 값을 더해준다.
		for(int i = 0; i < 4; i++)
		{
			if(random.nextFloat() <= PROB_FLAT)
				// 평평한 면이 나온 경우
				yootSticks[i] = SIDE_FLAT;
			else
				// 둥근 면이 나온 경우
				yootSticks[i] = SIDE_ROUND;
			
			sum += yootSticks[i];
		}
		
		// 경우의 수를 확인 해서 각각의 경우에 해당하는 윷의 값을 반환한다.
		// 모: 모든 윷가락이 둥근 면이 나온 경우 (총합이 0인경우)
		// 윷: 모든 윷가락이 평평한 면이 나온 경우 (총합이 4인 경우)
		// 걸: 3개의 윷가락이 평평한 면이 나온 경우 (총합이 3인 경우)
		// 개: 2개의 윷가락이 평평한 면이 나온 경우 (총합이 2인 경우)
		// 빽도: 빽도에 해당하는 윷가락, 즉 첫번째 윷가락만 평평한 면이 나온 경우(총합이 1인 경우에서 첫번째만 평평한 면)
		// 도: 빽도에 해당하는 윷가락이 아닌 나머지 윷가락 중 1개만 평평한 면이 나온 경우(총합이 1인 경우에서 첫번째 면이 둥근 면인 경우)
		if(sum == 4 * SIDE_FLAT)
		{
			// 윷이 나온 경우
			return YOOT;
		}
		else if(sum == 3 * SIDE_FLAT)
		{
			// 걸이 나온 경우
			return GUL;
		}
		else if(sum == 2 * SIDE_FLAT)
		{
			// 개가 나온 경우
			return GAE;
		}
		else if(sum == 1 * SIDE_FLAT)
		{
			if(yootSticks[0] == SIDE_FLAT)
			{
				// 빽도가 나온 경우
				return BACKDO;
			}
			else
			{
				// 도가 나온 경우
				return DO;
			}
		}
		else
		{
			// 모가 나온 경우
			return MO;
		}
	}
	
	
	// 빽도가 나오게 설정하는 메소드
	public static int throwBackdo() {
		yootSticks[0] = SIDE_FLAT;
		yootSticks[1] = SIDE_ROUND;
		yootSticks[2] = SIDE_ROUND;
		yootSticks[3] = SIDE_ROUND;
		
		return BACKDO;
	}
	
	
	// 도가 나오게 설정하는 메소드
	public static int throwDo() {
		yootSticks[0] = SIDE_ROUND;
		yootSticks[1] = SIDE_FLAT;
		yootSticks[2] = SIDE_ROUND;
		yootSticks[3] = SIDE_ROUND;
		
		return DO;
	}
	
	
	// 개가 나오게 설정하는 메소드
	public static int throwGae() {
		yootSticks[0] = SIDE_FLAT; 
		yootSticks[1] = SIDE_FLAT;
		yootSticks[2] = SIDE_ROUND;
		yootSticks[3] = SIDE_ROUND;
		
		return GAE;
	}
	
	
	// 걸이 나오게 설정하는 메소드
	public static int throwGul() {
		yootSticks[0] = SIDE_ROUND;
		yootSticks[1] = SIDE_FLAT;
		yootSticks[2] = SIDE_FLAT;
		yootSticks[3] = SIDE_FLAT;
		
		return GUL;
	}
	
	
	// 윷이 나오게 설정하는 메소드
	public static int throwYoot() {
		yootSticks[0] = SIDE_FLAT;
		yootSticks[1] = SIDE_FLAT;
		yootSticks[2] = SIDE_FLAT;
		yootSticks[3] = SIDE_FLAT;
		
		return YOOT;
	}
	
	
	// 모가 나오게 설정하는 메소드
	public static int throwMo() {
		yootSticks[0] = SIDE_ROUND;
		yootSticks[1] = SIDE_ROUND;
		yootSticks[2] = SIDE_ROUND;
		yootSticks[3] = SIDE_ROUND;
		
		return MO;
	}
}