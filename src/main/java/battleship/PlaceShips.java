package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class PlaceShips {
    static final String ERROR_FORMATTER = "Error! %s Try again:\n";
    static final String ERROR_WRONG_LENGTH_FORMATTED = "Wrong length of the %s!";
    static final String ERROR_TOO_CLOSE = "You placed it too close to another one.";
    static final String ERROR_SHIP_LOCATION = "Wrong ship location!";
    static String error;
    private static final Scanner sc = new Scanner(System.in);

    public static void placeShips(Player player) {
        Coordinate[][] board = player.getBoard();
        System.out.printf("%s, place your ships on the game field\n", player.getName());
        Board.setGlobalVisible(true);
        Board.printBoard(board);
        Ship[] ships = player.getShips();
        for (Ship ship : ships) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n\n", ship.getName(), ship.getSize());
            boolean isPlaced = getPlacementInput(board, ship);
            while (!isPlaced) {
                System.out.printf(ERROR_FORMATTER, error);
                isPlaced = getPlacementInput(board, ship);
            }
        }
        Board.setGlobalVisible(false);
        waitForKeypress();
    }

    static boolean getPlacementInput(Coordinate[][] board, Ship ship) {
        System.out.print("> ");
        int[] x = new int[2];
        int[] y = new int[2];
        String[] strings = sc.nextLine().trim().split(" ");
        if (strings.length < 2) {
            error = ERROR_SHIP_LOCATION;
            return false;
        }
        for (int i = 0; i < 2; i++) {
            String s = strings[i];
            y[i] = s.toUpperCase().charAt(0) - 65;
            try {
                x[i] = Integer.parseInt(s.replaceAll("[\\D]", "")) - 1;
            } catch (NumberFormatException e) {
                error = ERROR_SHIP_LOCATION;
                return false;
            }
        }
        x = Arrays.stream(x).sorted().toArray();
        y = Arrays.stream(y).sorted().toArray();

        if (y[0] == y[1] || x[0] == x[1]) {
            return checkPlacement(board, ship, x, y);
        }

        error = ERROR_SHIP_LOCATION;
        return false;
    }

    private static boolean checkPlacement(Coordinate[][] board, Ship ship, int[] x, int[] y) {
        if (ship.getSize() == Math.abs(x[0] - x[1]) + 1 ^ ship.getSize() == Math.abs(y[0] - y[1]) + 1) {
            for (int row = x[0]; row <= x[1]; row++) {
                for (int col = y[0]; col <= y[1]; col++) {
                    if (checkShipCollision(board, col, row)) {
                        error = ERROR_TOO_CLOSE;
                        return false;
                    }
                }
            }
            Coordinate[] tempCoords = new Coordinate[ship.getSize()];
            int idx = 0;
            for (int row = x[0]; row <= x[1]; row++) {
                for (int col = y[0]; col <= y[1]; col++) {
                    board[col][row] = new Coordinate(col, row);
                    board[col][row].setShip();
                    tempCoords[idx] = new Coordinate(col, row);
                    idx++;
                }
            }
            ship.setCoordinates(tempCoords);
            Board.printBoard(board);
            return true;
        } else {
            error = String.format(ERROR_WRONG_LENGTH_FORMATTED, ship.getName());
            return false;
        }
    }

    private static boolean checkShipCollision(Coordinate[][] board, int row, int col) {
        if (board[row][col].isShip()) { // check given point
            return true;
        }
        if (col != 0 && board[row][col - 1].isShip()) { // check 1 left
            return true;
        }
        if (col != 9 && board[row][col + 1].isShip()) { // check 1 right
            return true;
        }
        if (row != 0 && board[row - 1][col].isShip()) { // check 1 above
            return true;
        }
        if (row != 9 && board[row + 1][col].isShip()) { // check 1 below
            return true;
        }
        return false;
    }

    protected static void waitForKeypress() {
        System.out.println("Press Enter and pass the move to another player");
        sc.nextLine();
        System.out.println("...");
    }
}
