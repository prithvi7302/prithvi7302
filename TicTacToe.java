import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;

    public TicTacToe() {
        board = new char[SIZE][SIZE];
        scanner = new Scanner(System.in);
        currentPlayer = PLAYER_X;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("-".repeat(SIZE * 4 - 3));
            }
        }
    }

    private boolean isMoveValid(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Check rows
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Check columns
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Check diagonal
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Check anti-diagonal
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public void playGame() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Player X - You");
        System.out.println("Player O - Computer");

        while (true) {
            printBoard();

            if (currentPlayer == PLAYER_X) {
                System.out.println("Player X's turn (row[1-3] column[1-3]): ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;
                if (isMoveValid(row, col)) {
                    makeMove(row, col);
                } else {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }
            } else {
                int row, col;
                do {
                    row = (int) (Math.random() * SIZE);
                    col = (int) (Math.random() * SIZE);
                } while (!isMoveValid(row, col));
                System.out.println("Player O's turn (Computer): ");
                makeMove(row, col);
            }

            if (checkWin(PLAYER_X)) {
                printBoard();
                System.out.println("Player X wins!");
                break;
            } else if (checkWin(PLAYER_O)) {
                printBoard();
                System.out.println("Player O wins!");
                break;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}
