package battleship;

public class Board {

    private Coordinate[][] board;
    private static boolean globalVisible = true;

    void setBoard() {
        board = new Coordinate[10][10];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[col][row] = new Coordinate(col, row);
            }
        }
    }

    public Coordinate[][] getBoard() {
        if (board == null) {
            setBoard();
        }
        return board;
    }

    public static void printBoard(Coordinate[][] board) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char label = 'A';
        for (Coordinate[] row : board) {
            System.out.print(label++);
            for (Coordinate cell : row) {
                if (globalVisible) {
                    System.out.print(" " + cell.getCell(true));
                } else {
                    System.out.print(" " + cell.getCell(false));
                }
            }
            System.out.println();
        }
    }

    public static void setGlobalVisible(boolean globalVisible) {
        Board.globalVisible = globalVisible;
    }
}


