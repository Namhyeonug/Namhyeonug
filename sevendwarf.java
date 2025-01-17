package algostudy;
import java.util.Scanner;

// 9개 숫자가 주어짐. 이 중 7개의 합이 100이 되는 경우
// 9개 숫자의 합에서 100을 뺀 값

public class sevendwarf {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = 0;
		int[] answers = new int[9];
		for (int i = 0; i < 9; i++) {
			int num = sc.nextInt();
			count += num;
			answers[i] = num;
		}
		int check_num = count - 100;
		for (int j = 0; j < 8; j++) {
			for (int k = 1; k < 9-j ; k++) {
				
			}
		}
	}
}
