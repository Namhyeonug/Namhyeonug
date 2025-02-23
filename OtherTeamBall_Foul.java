package sincostan;

public class OtherTeamBall_Foul {
	public static void main(String[] args) {
		double r = 2.865; // 반지름
		double[] myball = {10,5}; // 내 공 좌표
		double[] target = {146,73}; // 목적구 좌표
		double[] goal = {254,127}; // 홀 좌표
		double[] other_team_ball = {66, 33}; // 상대팀 공 좌표
		double b = Math.sqrt(Math.pow(goal[0] - target[0],2) + Math.pow(goal[1] - target[1],2)); // 홀과 목적구 거리
		double x3 = target[0] + 2*r/b*(target[0]-goal[0]); // 접점 x좌표
		double y3 = target[1] + 2*r/b*(target[1]-goal[1]); // 접점 y좌표
		double d = Math.sqrt(Math.pow(x3 - myball[0],2) + Math.pow(y3 - myball[1],2)); // 접점과 내 공 거리
		double e = Math.sqrt(Math.pow(x3 - other_team_ball[0],2) + Math.pow(y3 - other_team_ball[1],2)); // 접점과 상대 팀 공 거리
		double f = Math.sqrt(Math.pow(myball[0] - other_team_ball[0],2) + Math.pow(myball[1] - other_team_ball[1],2)); // 내 공과 상대 팀 공 거리
		double cosTheta = (Math.pow(d, 2) + Math.pow(f, 2) - Math.pow(e, 2))/(2*d*f); // 코사인 세타
		double sinTheta = Math.sqrt(1-Math.pow(cosTheta, 2)); // 사인 세타
		boolean foul = false; // 충돌 여부
		if (f*cosTheta > 0 && f*cosTheta < d && Math.abs(f*sinTheta) <= 2*r) {
			foul = true;
		}
		// if 안의 조건들을 모두 만족한다면 그것은 내 공이 가는 경로에 다른 팀 공과 충돌한다는 의미.
		if (foul) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
	}
}
