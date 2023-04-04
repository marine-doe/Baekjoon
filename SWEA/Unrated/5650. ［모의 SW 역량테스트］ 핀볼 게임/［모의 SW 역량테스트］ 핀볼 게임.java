import java.util.Scanner;
 
public class Solution {
    static int[][] map;
    static int[] row = { -1, 1, 0, 0 }, col = { 0, 0, -1, 1 };
    static int n, result, sx, sy;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int t = sc.nextInt();
 
        for (int tc = 1; tc < t + 1; tc++) {
 
            StringBuilder sb = new StringBuilder("#" + tc + " ");
 
            n = sc.nextInt();
 
            map = new int[n][n];
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
 
            result = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            sx = i;
                            sy = j;
                            int nr = i + row[k];
                            int nc = j + col[k];
                            if (nr >= 0 && nc >= 0 && nr < n && nc < n) {
                                play(nr, nc, k);
                            }else {
                            	result = Math.max(result, 1);
                            }
                        }
                    }
                }
            }
//          result++;
            sb.append(result + "");
            System.out.println(sb);
        }
    }
 
    private static void play(int x, int y, int dir) {
        int sum = 0;
        outer: while (true) {
            switch (dir) {
            case 0:
                for (int i = x; i >= 0; i--) {
                    if (map[i][y] == -1 || (i == sx && y == sy)) { // 블랙홀이거나 시작점일 때
                        result = Math.max(result, sum);
                        return;
                    } else if (map[i][y] == 0) {
                        continue;
                    } else if (map[i][y] == 2) {
                        x = i;
                        y++;
                        dir = 3;
                        sum++;
                        continue outer;
                    } else if (map[i][y] == 3) {
                        x = i;
                        y--;
                        dir = 2;
                        sum++;
                        continue outer;
                    } else if (map[i][y] >= 6) {
                        int wormholl = map[i][y];
                        for (int r = 0; r < n; r++) {
                            for (int c = 0; c < n; c++) {
                                if ((r != i || c != y) && map[r][c] == wormholl) {
                                    x = r - 1;
                                    y = c;
                                    dir = 0;
                                    continue outer;
                                }
                            }
                        }
                    } else { // 1, 4, 5
                        x = i + 1;
                        dir = 1;
                        sum++;
                        continue outer;
                    }
                }
                // for문을 다 돌았거나 범위 밖이면 그냥 벽에 부딪혔겠거니~ 하자
                x = 0;
                dir = 1;
                sum++;
                break;
            case 1:
                for (int i = x; i < n; i++) {
                    if (map[i][y] == -1 || (i == sx && y == sy)) { // 블랙홀이거나 시작점일 때
                        result = Math.max(result, sum);
                        return;
                    } else if (map[i][y] == 0) {
                        continue;
                    } else if (map[i][y] == 1) {
                        x = i;
                        y++;
                        dir = 3;
                        sum++;
                        continue outer;
                    } else if (map[i][y] == 4) {
                        x = i;
                        y--;
                        dir = 2;
                        sum++;
                        continue outer;
                    } else if (map[i][y] >= 6) {
                        int wormholl = map[i][y];
                        for (int r = 0; r < n; r++) {
                            for (int c = 0; c < n; c++) {
                                if ((r != i || c != y) && map[r][c] == wormholl) {
                                    x = r + 1;
                                    y = c;
                                    dir = 1;
                                    continue outer;
                                }
                            }
                        }
                    } else { // 1, 4, 5
                        x = i - 1;
                        dir = 0;
                        sum++;
                        continue outer;
                    }
                }
                // for문을 다 돌았거나 범위 밖이면 그냥 벽에 부딪혔겠거니~ 하자
                x = n - 1;
                dir = 0;
                sum++;
                break;
            case 2:
                for (int i = y; i >= 0; i--) {
                    if (map[x][i] == -1 || (x == sx && i == sy)) { // 블랙홀이거나 시작점일 때
                        result = Math.max(result, sum);
                        return;
                    } else if (map[x][i] == 0) {
                        continue;
                    } else if (map[x][i] == 2) {
                        y = i;
                        x++;
                        dir = 1;
                        sum++;
                        continue outer;
                    } else if (map[x][i] == 1) {
                        y = i;
                        x--;
                        dir = 0;
                        sum++;
                        continue outer;
                    } else if (map[x][i] >= 6) {
                        int wormholl = map[x][i];
                        for (int r = 0; r < n; r++) {
                            for (int c = 0; c < n; c++) {
                                if ((r != x || c != i) && map[r][c] == wormholl) {
                                    x = r;
                                    y = c - 1;
                                    dir = 2;
                                    continue outer;
                                }
                            }
                        }
                    } else { // 1, 4, 5
                        y = i + 1;
                        dir = 3;
                        sum++;
                        continue outer;
                    }
                }
                // for문을 다 돌았거나 범위 밖이면 그냥 벽에 부딪혔겠거니~ 하자
                y = 0;
                dir = 3;
                sum++;
                break;
            case 3:
                for (int i = y; i < n; i++) {
                    if (map[x][i] == -1 || (x == sx && i == sy)) { // 블랙홀이거나 시작점일 때
                        result = Math.max(result, sum);
                        return;
                    } else if (map[x][i] == 0) {
                        continue;
                    } else if (map[x][i] == 3) {
                        y = i;
                        x++;
                        dir = 1;
                        sum++;
                        continue outer;
                    } else if (map[x][i] == 4) {
                        y = i;
                        x--;
                        dir = 0;
                        sum++;
                        continue outer;
                    } else if (map[x][i] >= 6) {
                        int wormholl = map[x][i];
                        for (int r = 0; r < n; r++) {
                            for (int c = 0; c < n; c++) {
                                if ((r != x || c != i) && map[r][c] == wormholl) {
                                    x = r;
                                    y = c + 1;
                                    dir = 3;
                                    continue outer;
                                }
                            }
                        }
                    } else { // 1, 4, 5
                        y = i - 1;
                        dir = 2;
                        sum++;
                        continue outer;
                    }
                }
                // for문을 다 돌았거나 범위 밖이면 그냥 벽에 부딪혔겠거니~ 하자
                y = n - 1;
                dir = 2;
                sum++;
                break;
            }
        }
    }
}