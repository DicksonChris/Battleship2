package battleship;

public class Battleship {
    public static void main(String[] args) {
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        // place ships
        PlaceShips.placeShips(player1);
        PlaceShips.placeShips(player2);

        while(!Combat.allIsSank) {
            Combat.startCombat(player1, player2);
            Combat.startCombat(player2, player1);
        }
    }
}
