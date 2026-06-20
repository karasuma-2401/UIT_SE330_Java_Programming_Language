package Ex10;

public class Cell {
    private int r;
    private int c;
    int distance;
    Cell parent;
    public Cell(int row, int col, int distance, Cell parent) {
        this.r = row;
        this.c = col;
        this.distance = distance;
        this.parent = parent;
    }
    public int getRow() {return r;}
    public int getCol() {return c;}
    public int getDistance() {return distance;}
    public Cell getParent() {return parent;}
}
