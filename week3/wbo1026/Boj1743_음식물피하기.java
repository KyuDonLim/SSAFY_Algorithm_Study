package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1743 {
    static int MAX;
    static int[] MX = new int[] { -1, 1, 0, 0 }; // 상 하 좌 우
    static int[] MY = new int[] { 0, 0, -1, 1 };

    public static void search(Queue<Integer> qX, Queue<Integer> qY, int[][] map, boolean[][] visited) {
        int area = 0;

        while (!qX.isEmpty()) {
            area++;
            int x = qX.poll();
            int y = qY.poll();

            for (int i = 0; i < 4; i++) {
                int dx = x + MX[i];
                int dy = y + MY[i];
                if (dx >= 0 && dx < map.length && dy >= 0 && dy < map[0].length && !visited[dx][dy]
                        && map[dx][dy] == 1) {
                    qX.add(dx);
                    qY.add(dy);
                    visited[dx][dy] = true;
                }
            }

        }
        if (MAX < area)
            MAX = area;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // mpa 세로
        int m = Integer.parseInt(st.nextToken()); // map 가로
        int k = Integer.parseInt(st.nextToken()); // 쓰레기 개수

        Queue<Integer> qX = new LinkedList<>(); // 다음 대기열을 담을 q
        Queue<Integer> qY = new LinkedList<>();
        int[][] map = new int[n+1][m+1]; // map 이차원 배열
        boolean[][] visited = new boolean[n+1][m+1]; // false: 방문안함, true: 방문함

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;

        }

        // end input

        MAX = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    qX.add(i);
                    qY.add(j);
                    visited[i][j] = true;
                    search(qX, qY, map, visited);
                }
            }
        }

        System.out.println(MAX);

    }
}
