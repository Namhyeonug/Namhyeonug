package player;
import model.Ball;
import model.Constant;
import player.Player;
import view.Display;

import java.awt.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Player {
	//플레이어의 순서
	private int order = 0;

	/*
	 각 공의 위치를 나타냅니다.
	 balls[0]은 수구(흰공), balls[balls.length - 1]은 검은 공입니다.
	 플레이어가 2명일 경우 order = 0이면 홀수, 1이면 짝수번 공이 목적구입니다(마지막 공 제외)
	*/
	int r = 3;
	private double[][] balls;
	private int[][] HOLES = { {r, r}, {127, r}, {254-r, r}, {r, 127-r}, {127, 127-r}, {254-r, 127-r} };
	private double power = 100f;
	private double angle = 0f;
	private	double minCollAngle = 181.0;
	private	double bestShootAngle = 0.0;

	public Player(int order, double[][] balls){
		this.order = order;
		this.balls = balls;
	}

	//do not modify above
	//please modify below

	/**
	 * 적절히 공을 넣을 수 있도록 getAngle()과 getPower() 메서드를 변경해야 합니다.
	 * 스스로 로직을 짜봅시다.
	*/
	public double getAngle() {
		double[][] Objectball = new double[10][2];
		double[][] NonObjectball = new double[10][2];
		double[][] LeftObjectball = new double[10][2];
		double[][] RightObjectball = new double[10][2];
		double[][] UpObjectball = new double[10][2];
		double[][] DownObjectball = new double[10][2];
		double[][] LeftNonObjectball = new double[10][2];
		double[][] RightNonObjectball = new double[10][2];
		double[][] UpNonObjectball = new double[10][2];
		double[][] DownNonObjectball = new double[10][2];
		int object_index = 0;
		int nonobject_index = 0;
		int[][] LEFT_HOLES = new int[6][2];
		int[][] RIGHT_HOLES = new int[6][2];
		int[][] UP_HOLES = new int[6][2];
		int[][] DOWN_HOLES = new int[6][2];
		for (int x = 0; x < HOLES.length; x++){
			LEFT_HOLES[x][0] = HOLES[x][0]-(254-2*r);
			LEFT_HOLES[x][1] = HOLES[x][1];
			RIGHT_HOLES[x][0] = HOLES[x][0]+(254-2*r);
			RIGHT_HOLES[x][1] = HOLES[x][1];
			UP_HOLES[x][0] = HOLES[x][0];
			UP_HOLES[x][1] = HOLES[x][1]+(127-2*r);
			DOWN_HOLES[x][0] = HOLES[x][0];
			DOWN_HOLES[x][1] = HOLES[x][1]-(127-2*r);
		}
		//TODO
		for (int i = 1; i < balls.length; i++) {
			if (isObjectBall(i)) {
				Objectball[object_index] = balls[i];
				object_index++;
			} else {
				NonObjectball[nonobject_index] = balls[i];
				nonobject_index++;
			}
		}
		for (int k = 0; k < object_index; k++){
			LeftObjectball[k][0] = -Objectball[k][0];
			LeftObjectball[k][1] = Objectball[k][1];
			RightObjectball[k][0] = 508-Objectball[k][0];
			RightObjectball[k][1] = Objectball[k][1];
			UpObjectball[k][0] = Objectball[k][0];
			UpObjectball[k][1] = 254-Objectball[k][1];
			DownObjectball[k][0] = Objectball[k][0];
			DownObjectball[k][1] = -Objectball[k][1];
		}
		for (int v = 0; v < nonobject_index; v++){
			LeftNonObjectball[v][0] = -NonObjectball[v][0];
			LeftNonObjectball[v][1] = NonObjectball[v][1];
			RightNonObjectball[v][0] = 508-NonObjectball[v][0];
			RightNonObjectball[v][1] = NonObjectball[v][1];
			UpNonObjectball[v][0] = NonObjectball[v][0];
			UpNonObjectball[v][1] = 254-NonObjectball[v][1];
			DownNonObjectball[v][0] = NonObjectball[v][0];
			DownNonObjectball[v][1] = -NonObjectball[v][1];
		}
		for (int c = 0; c < object_index; c++){
			for (int p = 0; p < HOLES.length; p++){
				boolean foul = false;
				for (int h = 0; h < nonobject_index; h++){
					boolean check = NonObjectFoul(Objectball[c], NonObjectball[h], HOLES[p]);
					if (check){
						foul = true;
					}
				}
				if (!foul){
					double collAngle = calCollAngle(Objectball[c], HOLES[p]);
					if (collAngle >= 0.0 && collAngle < minCollAngle){
						minCollAngle = collAngle;
						bestShootAngle = calShootAngle(Objectball[c], HOLES[p]);
					}
				}
			}
		}
//		for (int c = 0; c < object_index; c++){
//			for (int p = 0; p < LEFT_HOLES.length; p++){
//				boolean foul = false;
//				for (int h = 0; h < nonobject_index; h++){
//					boolean check = NonObjectFoul(LeftObjectball[c], LeftNonObjectball[h], LEFT_HOLES[p]);
//					if (check){
//						foul = true;
//					}
//				}
//				if (!foul){
//					double collAngle = calCollAngle(LeftObjectball[c], LEFT_HOLES[p]);
//					if (collAngle < minCollAngle){
//						minCollAngle = collAngle;
//						bestShootAngle = calShootAngle(LeftObjectball[c], LEFT_HOLES[p]);
//					}
//				}
//			}
//		}
//		for (int c = 0; c < object_index; c++){
//			for (int p = 0; p < RIGHT_HOLES.length; p++){
//				boolean foul = false;
//				for (int h = 0; h < nonobject_index; h++){
//					boolean check = NonObjectFoul(RightObjectball[c], RightNonObjectball[h], RIGHT_HOLES[p]);
//					if (check){
//						foul = true;
//					}
//				}
//				if (!foul){
//					double collAngle = calCollAngle(RightObjectball[c], RIGHT_HOLES[p]);
//					if (collAngle < minCollAngle){
//						minCollAngle = collAngle;
//						bestShootAngle = calShootAngle(RightObjectball[c], RIGHT_HOLES[p]);
//					}
//				}
//			}
//		}
//		for (int c = 0; c < object_index; c++){
//			for (int p = 0; p < UP_HOLES.length; p++){
//				boolean foul = false;
//				for (int h = 0; h < nonobject_index; h++){
//					boolean check = NonObjectFoul(UpObjectball[c], UpNonObjectball[h], UP_HOLES[p]);
//					if (check){
//						foul = true;
//					}
//				}
//				if (!foul){
//					double collAngle = calCollAngle(UpObjectball[c], UP_HOLES[p]);
//					if (collAngle < minCollAngle){
//						minCollAngle = collAngle;
//						bestShootAngle = calShootAngle(UpObjectball[c], UP_HOLES[p]);
//					}
//				}
//			}
//		}
//		for (int c = 0; c < object_index; c++){
//			for (int p = 0; p < DOWN_HOLES.length; p++){
//				boolean foul = false;
//				for (int h = 0; h < nonobject_index; h++){
//					boolean check = NonObjectFoul(DownObjectball[c], DownNonObjectball[h], DOWN_HOLES[p]);
//					if (check){
//						foul = true;
//					}
//				}
//				if (!foul){
//					double collAngle = calCollAngle(DownObjectball[c], DOWN_HOLES[p]);
//					if (collAngle < minCollAngle){
//						minCollAngle = collAngle;
//						bestShootAngle = calShootAngle(DownObjectball[c], DOWN_HOLES[p]);
//					}
//				}
//			}
//		}
		System.out.printf("%.6f\n",minCollAngle);
		System.out.printf("%.6f\n",bestShootAngle);
		return bestShootAngle;
	}
	public double calShootAngle(double[] Objectball, int[] HOLES){
		double b = Math.sqrt(Math.pow(HOLES[0] - Objectball[0],2) + Math.pow(HOLES[1] - Objectball[1],2));
		double x3 = Objectball[0] + Ball.DIAMETER/b*(Objectball[0]-HOLES[0]);
		double y3 = Objectball[1] + Ball.DIAMETER/b*(Objectball[1]-HOLES[1]);
		double shoot_degree = Math.atan((y3-balls[0][1])/(x3-balls[0][0])); // 내 공의 발사각
		double shootdegreetopi = (shoot_degree*180/Math.PI+360)%360; // 발사각 라디안에서 도로 변환
		return shootdegreetopi;
	}
	public double calCollAngle(double[] Objectball, int[] HOLES){
		double a = Math.sqrt(Math.pow(HOLES[0] - balls[0][0],2) + Math.pow(HOLES[1] - balls[0][1],2));
		double b = Math.sqrt(Math.pow(HOLES[0] - Objectball[0],2) + Math.pow(HOLES[1] - Objectball[1],2));
		double c = Math.sqrt(Math.pow(Objectball[0] - balls[0][0],2) + Math.pow(Objectball[1] - balls[0][1],2));
		double x3 = Objectball[0] + Ball.DIAMETER/b*(Objectball[0]-HOLES[0]);
		double y3 = Objectball[1] + Ball.DIAMETER/b*(Objectball[1]-HOLES[1]);
		double b2r = b + Ball.DIAMETER;
		double degree1 = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2))/(2*a*b));
		double d = Math.sqrt(Math.pow(a, 2) + Math.pow(b2r, 2) - 2*a*b2r*Math.cos(degree1));
		double degree2 = Math.acos((Math.pow(a, 2) + Math.pow(d, 2) - Math.pow(b2r, 2))/(2*a*d)); // 삼각함수 자료의 (나) 각
		double degree3 = degree1 + degree2; //(다)+(나) : 내 공과 목적구의 충돌각
		double cosdegree3 = Math.cos(degree3); // 충돌각의 코사인 값
		double final_degree = (Math.acos(cosdegree3)*180/Math.PI+360)%360;
		return final_degree;
	}
	public boolean NonObjectFoul(double[] Objectball, double[] NonObjectball, int[] HOLES){
		double b = Math.sqrt(Math.pow(HOLES[0] - Objectball[0],2) + Math.pow(HOLES[1] - Objectball[1],2));
		double x3 = Objectball[0] + Ball.DIAMETER/b*(Objectball[0]-HOLES[0]);
		double y3 = Objectball[1] + Ball.DIAMETER/b*(Objectball[1]-HOLES[1]); // 접점 y좌표
		double d = Math.sqrt(Math.pow(x3 - balls[0][0],2) + Math.pow(y3 - balls[0][1],2));
		double e = Math.sqrt(Math.pow(x3 - NonObjectball[0],2) + Math.pow(y3 - NonObjectball[1],2));
		double f = Math.sqrt(Math.pow(balls[0][0] - NonObjectball[0],2) + Math.pow(balls[0][1] - NonObjectball[1],2));
		double cosTheta = (Math.pow(d, 2) + Math.pow(f, 2) - Math.pow(e, 2))/(2*d*f); // 코사인 세타
		double sinTheta = Math.sqrt(1-Math.pow(cosTheta, 2)); // 사인 세타
		boolean foul = false; // 충돌 여부
		if (f*cosTheta > 0 && f*cosTheta < d && Math.abs(f*sinTheta) <= Ball.DIAMETER) {
			foul = true;
		}
		return foul;
	}
	public double getPower() {
		double power = 600.0;
		return power;
	}

	private double getDist(int i, int j) {
		double xDiff = balls[i][0] - balls[j][0];
		double yDiff = balls[i][1] - balls[j][1];

		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}

	private double getAngle(int i, int j){
		double xDiff = balls[j][0] - balls[i][0];
		double yDiff = balls[j][1] - balls[i][1];

		return Math.toDegrees(Math.atan2(yDiff, xDiff));
	}

	private boolean isObjectBall(int n){
		if (balls[n][0] == 0) return false;

		int ballCount = 0;
		for (int i = 1; i < balls.length - 1; i++){
			if (balls[i][0] == 0) continue;
			if (order == 0 && i % 2 == 1) ballCount++;
			else if (order == 1 && i % 2 == 0) ballCount++;
		}

		if (ballCount == 0) return n == balls.length - 1;

		if (n == balls.length - 1) return false;

		if (order == 0) return n % 2 == 1;
		return n % 2 == 0;
	}
}
