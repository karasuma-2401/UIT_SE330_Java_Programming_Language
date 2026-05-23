package Ex5;

public class Cell {
    private int row, col;
    boolean[] walls = {true, true, true, true};
    boolean visited = false;
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
