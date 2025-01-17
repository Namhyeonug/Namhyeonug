package algostudy;
import java.util.Scanner;
//3 이상의 홀수
//3 이상의 짝수
//2
//1
// if-else 를 통해 각 네 개의 케이스 중 하나로 들어간다.
// 3 이상의 짝수는 무조건 소수가 아니다.
// 2는 유일한 소수인 짝수
// 1은 안 됨
// 3 이상의 홀수에 대해서 반복문을 통해 (2부터 수-1) 나머지 체크
// n-1까지 갔을 때도 나머지가 0이 아니라면 소수 카운트, 중간에서 나머지 0인 경우 발생하면 반복문 break
public class primenumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int count = 0;
		for (int j = 0; j < N; j++) {
			int num = sc.nextInt();
			if (num >= 3) {
				if (num % 2 == 0) {
					continue;
				} else {
					for (int i = 2; i < num; i++) {
						if (num % i == 0) {
							break;
						} else {
							if (i == num-1) {
								count += 1;
							}
						}
					}
				}
			} else if (num == 2) {
				count += 1;
			} else {
				continue;
			}
		}
		System.out.println(count);
		sc.close();
	}
}
