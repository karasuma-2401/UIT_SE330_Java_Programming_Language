package Ex10;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class MazeBFS {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public static void solve(char[][] m, int sr, int sc, int er, int ec) {
        int R = m.length, C = m[0].length;
        boolean[][] vis = new boolean[R][C];
        Queue<Cell> q = new LinkedList<>();
        
        q.offer(new Cell(sr, sc, 0, null));
        vis[sr][sc] = true;
        Cell end = null;

        while (!q.isEmpty()) {
            Cell cur = q.poll();

            if (cur.getRow() == er && cur.getCol() == ec) {
                end = cur;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.getRow() + dr[i];
                int nc = cur.getCol() + dc[i];

                if (nr >= 0 && nr < R && nc >= 0 && nc < C && m[nr][nc] != '#' && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.offer(new Cell(nr, nc, cur.getDistance() + 1, cur));
                }
            }
        }

        if (end != null) {
            System.out.println("=> Shortest path: " + end.getDistance());
            Cell t = end;
            while (t != null) {
                if (m[t.getRow()][t.getCol()] != 'S' && m[t.getRow()][t.getCol()] != 'E') m[t.getRow()][t.getCol()] = '*';
                t = t.getParent();
            }
            print(m);
        } else {
            System.out.println("=> No path found.");
        }
    }

    public static void print(char[][] m) {
        for (char[] row : m) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] matrix2D = {
            {'.', '.', '.', '#', '.', '.', '.'},
            {'.', '#', '.', '#', '.', '#', '.'},
            {'.', '#', '#', '.', '.', '#', '.'},
            {'.', '.', '.', '#', '.', '.', '.'},
            {'#', '#', '.', '.', '.', '#', '.'}
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter start row and column: ");
        int startR = sc.nextInt();
        int startC = sc.nextInt();
        System.out.print("Enter end row and column: ");
        int endR = sc.nextInt();
        int endC = sc.nextInt();
        matrix2D[startR][startC] = 'S';
        matrix2D[endR][endC] = 'E';

        solve(matrix2D, startR, startC, endR, endC);
        sc.close();
    }
}