package Ex5;

import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MazeGen {
    private int rows, cols;
    private Cell[][] grid;
    private Random rand = new Random();
    public MazeGen(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
    }
    public void generate() {
        generateFrom(0, 0);
        grid[0][0].walls[3] = false;
        grid[rows - 1][cols - 1].walls[1] = false;
    }
    public void generateFrom(int r, int c) {
        grid[r][c].visited = true;
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};
        int[] opposite = {2, 3, 0, 1};

        List <Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directions.add(i);
        }
        Collections.shuffle(directions, rand);
        for (int dir : directions) {
            int nextRow = r + dRow[dir];
            int nextCol = c + dCol[dir];

            if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && !grid[nextRow][nextCol].visited) {
                grid[r][c].walls[dir] = false;
                grid[nextRow][nextCol].walls[opposite[dir]] = false;
                generateFrom(nextRow, nextCol);
            }
        }
    }
    public void printMaze() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c].walls[0] ? "+---" : "+   ");
            }
            System.out.println("+");
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c].walls[3] ? "|   " : "    ");
            }
            System.out.println(grid[r][cols - 1].walls[1] ? "|" : " ");
        }
        for (int j = 0; j < cols; j++) {
            System.out.print("+");
            System.out.print(grid[rows - 1][j].walls[2] ? "---" : "   ");
        }
        System.out.println("+");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        System.out.print("Enter maze rows: ");
        int rows = sc.nextInt();
        System.out.println("Enter maze cols: ");
        int cols = sc.nextInt();

        MazeGen maze = new MazeGen(rows, cols);
        maze.generate();
        maze.printMaze();
    }
}

