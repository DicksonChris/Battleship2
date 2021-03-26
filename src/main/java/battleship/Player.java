package battleship;

public class Player {
    private final Ship[] ships;
    private final Coordinate[][] board;
    private boolean newSunk = false;
    private final String name;

    Player(int num) {
        this.ships = new Battleships().getShips();
        this.board = new Board().getBoard();
        this.name = String.format("Player %d", num);
    }

    public Ship[] getShips() {
        return ships;
    }

    public Coordinate[][] getBoard() {
        return board;
    }

    public void setNewSunk() {
        this.newSunk = true;
    }

    public void isNewSunk() {
        if (this.newSunk) {
            Combat.message = "You sank a ship!";
            this.newSunk = false;
        }
    }

    public boolean allIsSank() {
        int count = 0;
        for (Ship ship : this.getShips()) {
            if (ship.checkIfShipIsSunk(ship, this)) {
                count++;
            }
        }
        return count == 5;
    }

    public String getName() {
        return name;
    }
}
