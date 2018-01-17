package biubiu.test;

import java.util.Scanner;

/**
 * 商队运输，最长路径问题
 * 
 * @author dmf
 *
 */
public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map;
		int max;
		int res;
			
		while (sc.hasNext()) {
			int n = sc.nextInt();
			map = new int[n+1][n+1];
			int i, j;
			int a, b, c;
			
			for (i = 1; i <= n; i++) {
				for (j = 1; j <= n; j++) {
					map[i][j] = 2048;
					if (i == j)
						map[i][j] = 0;
				}
			}

			//初始化城市之间的直接距离
			for (i = 1; i <= n - 1; i++) {
				a = sc.nextInt();
				b = sc.nextInt();
				c = sc.nextInt();
				map[a][b] = c;
				map[b][a] = c;
			}

			//初始化城市之间的间接距离
			int k;
			for (k = 1; k <= n; k++) {
				for (i = 1; i <= n; i++) {
					for (j = 1; j <= n; j++) {
						if (map[i][j] > map[i][k] + map[k][j])
							map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
			//寻找最大距离
			max = 0;
			for (i = 1; i <= n; i++) {
				for (j = 1; j <= n; j++) {
					if ((res = cal(map[i][j])) > max)
						max = res;
				}
			}
			System.out.println(max);
		}
	}

	public static int cal(int n) {
		if (n == 0)
			return 0;
		else
			return cal(n - 1) + 10 + n;
	}

}
