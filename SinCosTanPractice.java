package sincostan;
public class SinCosTanPractice {
	public static void main(String[] args) {
		double r = 2.865; // 반지름
		double[] myball = {73,73}; // 내 공 좌표
		double[] target = {10,10}; // 목적구 좌표
		double[] goal = {257,257}; // 홀 좌표

		double a = Math.sqrt(Math.pow(goal[0] - myball[0],2) + Math.pow(goal[1] - myball[1],2)); // 홀과 내 공 거리
		double b = Math.sqrt(Math.pow(goal[0] - target[0],2) + Math.pow(goal[1] - target[1],2)); // 홀과 목적구 거리
		double c = Math.sqrt(Math.pow(target[0] - myball[0],2) + Math.pow(target[1] - myball[1],2)); // 목적구와 내 공 거리
		double degree1 = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2))/(2*a*b)); // 삼각함수 자료의 (다) 각
		double degree1topi = (degree1*180/Math.PI+360)%360; // (다) 각 라디안을 도로 변환
		double x3 = target[0] + 2*r/b*(target[0]-goal[0]); // 접점 x좌표
		double y3 = target[1] + 2*r/b*(target[1]-goal[1]); // 접점 y좌표
		double b2r = b + 5.73;
		double d = Math.sqrt(Math.pow(a, 2) + Math.pow(b2r, 2) - 2*a*b2r*Math.cos(degree1));
		double shoot_degree = Math.atan((y3-myball[1])/(x3-myball[0])); // 내 공의 발사각
		double shootdegreetopi = (shoot_degree*180/Math.PI+360)%360; // 발사각 라디안에서 도로 변환
		double degree2 = Math.acos((Math.pow(a, 2) + Math.pow(d, 2) - Math.pow(b2r, 2))/(2*a*d)); // 삼각함수 자료의 (나) 각
		double degree2topi = (degree2*180/Math.PI+360)%360; //(나) 각 라디안을 도로 변환
		double degree3 = degree1 + degree2; //(다)+(나) : 내 공과 목적구의 충돌각
		double cosdegree3 = Math.cos(degree3); // 충돌각의 코사인 값
		double final_degree = (Math.acos(cosdegree3)*180/Math.PI+360)%360; // 충돌각 도로 변환
		System.out.printf("%.6f", final_degree);
	}
}
