package battleship;

public class Coordinate {
    protected int row;
    protected int col;
    private boolean hit;
    private boolean ship;

    protected static final char SHIP = 'O';
    protected static final char MISS = 'M';
    protected static final char FIELD = '~';
    protected static final char HIT = 'X';

    public Coordinate(int col, int row) {
        this.row = row;
        this.col = col;
        this.hit = false;
        this.ship = false;
    }

    public boolean equalCoords(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Coordinate coordinate = (Coordinate) o;
        return row == coordinate.row && col == coordinate.col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setShip() {
        this.ship = true;
    }

    public void setHit() {
        this.hit = true;
    }

    public boolean isHit() {
        return hit;
    }

    public boolean isShip() {
        return ship;
    }

    public char getCell(boolean overrideVisibility) {
        if (ship && hit) {
            return HIT;
        }
        if (hit) {
            return MISS;
        }
        if (overrideVisibility && ship) {
            return SHIP;
        }
        return FIELD;
    }
}
