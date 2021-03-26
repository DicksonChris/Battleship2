package battleship;

public class Battleships {

    private Ship[] ships;

    void setShips() {
        ships = new Ship[]{
                new Ship(5, "Aircraft Carrier"),
                new Ship(4, "Battleship"),
                new Ship(3, "Submarine"),
                new Ship(3, "Cruiser"),
                new Ship(2, "Destroyer")
        };
    }

    public Ship[] getShips() {
        if (ships == null) {
            setShips();
        }
        return ships;
    }
}


