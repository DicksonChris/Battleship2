package battleship;

import java.util.Scanner;

import static battleship.PlaceShips.waitForKeypress;

public class Combat {
    private static final Scanner sc = new Scanner(System.in);
    protected static boolean allIsSank = false;
    public static String message = "";

    public static void startCombat(Player playerA, Player playerB) {
        Board.printBoard(playerB.getBoard());
        System.out.println("---------------------");
        Board.setGlobalVisible(true);
        printPlayerABoard(playerA.getBoard());
        Board.setGlobalVisible(false);
        System.out.printf("%s, it's your turn:\n", playerA.getName());
        combat(playerB);
    }

    private static void combat(Player playerB) {
        Coordinate shot = fireShot();
        for (Coordinate[] row : playerB.getBoard()) {
            for (Coordinate c : row) {
                if (c.equalCoords(shot)) {
                    if (!c.isHit() && c.isShip()) {
                        message = "You hit a ship!";
                    } else {
                        message = "You missed!";
                    }
                    c.setHit();
                    allIsSank = playerB.allIsSank();
                    if (!message.equals("")) {
                        playerB.isNewSunk();
                        if (allIsSank) {
                            message = "You sank the last ship. You won. Congratulations!";
                        }
                        System.out.println(message);
                    }
                }
            }
        }
        waitForKeypress();
    }

    private static Coordinate fireShot() {
        int row = -1;
        int col = -1;
        boolean gotInput = false;
        System.out.print("> ");
        while (!gotInput) {
            String s = sc.nextLine().trim();
            try {
                col = s.toUpperCase().charAt(0) - 65;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Error bad input");
            }
            try {
                row = Integer.parseInt(s.replaceAll("[\\D]", "")) - 1;
                if (0 <= row && row <= 9 && 0 <= col && col <= 9) {
                    gotInput = true;
                } else {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error bad input");
            }
        }
        return new Coordinate(col, row);
    }

    private static void printPlayerABoard(Coordinate[][] board) {
        Board.setGlobalVisible(true);
        Board.printBoard(board);
        Board.setGlobalVisible(false);
    }
}
