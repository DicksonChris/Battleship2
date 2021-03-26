package battleship;

import java.util.Arrays;

public class Ship {
    private final String name;
    private Coordinate[] coordinates;
    private final int size;
    private boolean sunk = false;

    public Ship(int size, String name) {
        this.coordinates = new Coordinate[size];
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Battleships.Ship{" +  name + ", coords=" + Arrays.toString(coordinates) + '}';
    }

    public int getSize() {
        return size;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk() {
        this.sunk = true;
    }

    public boolean checkIfShipIsSunk(Ship ship, Player p) {
        if (ship.isSunk()) {
            return true;
        }
        int count = 0;
        Coordinate[][] board = p.getBoard();
        for (Coordinate shipCoord : ship.getCoordinates()) {
            if (board[shipCoord.getCol()][shipCoord.getRow()].isHit()) {
                count++;
            }
        }
        if (count == ship.size) {
            p.setNewSunk();
            ship.setSunk();
            return true;
        }
        return false;
    }
}
