package sc.util;

public class CalculateExp {

	private int exp = 0; // 누적 경험치
	private int level = 0; // 현재 레벨
	private int remainExp = 0; // 현재 레벨의 남은 경험치
	private int nextExp = 0; // 레벨업까지 필요한 경험치
	
	public CalculateExp() { }
	
	public CalculateExp(int exp) {
		this.exp = exp;
		calculate();
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRemainExp() {
		return remainExp;
	}

	public void setRemainExp(int remainExp) {
		this.remainExp = remainExp;
	}

	public int getNextExp() {
		return nextExp;
	}

	public void setNextExp(int nextExp) {
		this.nextExp = nextExp;
	}

	public void calculate() {
		if(0<=exp && exp<=10) {
			level = 1;
			remainExp = exp;
			nextExp = 10 - exp;
		} else if(11<=exp && exp<=20) {
			level = 2;
			remainExp = exp - 10;
			nextExp = 20 - exp;
		} else if(21<=exp && exp<=40) {
			level = 3;
			remainExp = exp - 20;
			nextExp = 40 - exp;
		} else if(41<=exp && exp<=80) {
			level = 4;
			remainExp = exp - 40;
			nextExp = 80 - exp;
		} else if(81<=exp && exp<=160) {
			level = 5;
			remainExp = exp - 80;
			nextExp = 160 - exp;
		} else if(161<=exp && exp<=320) {
			level = 6;
			remainExp = exp - 160;
			nextExp = 320 - exp;
		} else if(321<=exp && exp<=640) {
			level = 7;
			remainExp = exp - 320;
			nextExp = 640 - exp;
		} else if(641<=exp && exp<=1280) {
			level = 8;
			remainExp = exp - 640;
			nextExp = 1280 - exp;
		} else if(1281<=exp && exp<=2560) {
			level = 9;
			remainExp = exp - 1280;
			nextExp = 2560 - exp;
		} else if(2561<=exp && exp<=5120) {
			level = 10;
			remainExp = exp - 2560;
			nextExp = 5120 - exp;
		} else if(5121<=exp) {
			level = 11;
			remainExp = exp - 5120;
			nextExp = 0;			
		} else { // 경험치가 -일 경우
			level = 0;
			remainExp = exp;
			nextExp = 0 - exp;		
		}
	}
	
	public static int levelToMinExp(int level) {
		switch(level) {
			case 0:
				return -100; 				
			case 1:
				return 0;
			case 2:
				return 11;
			case 3:
				return 21;
			case 4:
				return 41; 
			case 5:
				return 81;
			case 6:
				return 161;
			case 7:
				return 321; 
			case 8:
				return 641;
			case 9:
				return 1281;
			case 10:
				return 2561;
			case 11:
				return 5121; 			
			default:
				return -50000;				
		}		
	}
	
	public static int levelToMaxExp(int level) {
		switch(level) {
			case 0:
				return -1; 				
			case 1:
				return 10;
			case 2:
				return 20;
			case 3:
				return 40;
			case 4:
				return 80; 
			case 5:
				return 160;
			case 6:
				return 320;
			case 7:
				return 640; 
			case 8:
				return 1280;
			case 9:
				return 2560;
			case 10:
				return 5120;
			case 11:
				return 10240; 			
			default:
				return 50000;				
		}		
	}
}