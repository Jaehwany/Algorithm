package Algorizm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sol_1215 {
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		char[][] map = new char[8][8];

		for (int tc = 1; tc <= 10; tc++) {

			int length = Integer.parseInt(br.readLine());

			// 파일 데이터 map[][]에 mapping
			for (int i = 0; i < 8; ++i) {
				String str = br.readLine();
				for (int j = 0; j < 8; ++j)
					map[i][j] = str.charAt(j);
			}
			
			 

			if (length == 1) {
				System.out.println("#" + tc + " " + 64);
				continue;
			}

			boolean check;
			int ans = 0;
	
			//가로로 탐색 
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j <= 8 - length; j++) {
					check = true;
			
					for (int k = 0; k < length / 2; k++) {
						if (map[i][j + k] != map[i][j - k + length - 1])
							check = false;
					}

					if (check == true)
						ans++;
				
				}
			}
			
			//세로로 탐색
			for (int i = 0; i <= 8 - length; i++) {
				for (int j = 0; j < 8; j++) {

					check = true;

					for (int k = 0; k < length / 2; k++) {
						if (map[i + k][j] != map[i - k + length - 1][j])
							check = false;
					}
					if (check == true)
						ans++;
				}
			}
			
			//결과 출력
			System.out.println("#" + tc + " " + ans);
		}
		br.close();
	}
}
