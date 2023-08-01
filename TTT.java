import java.util.Scanner;

public class TTT {

    private final int SIZE = 3;
    private char[][] board;
    private char currentPlayer;
    private int movesMade;
    public static void main(String[] args) {
        TTT game = new TTT();
        game.play();
    }

    public TTT() {
        board = new char[SIZE][SIZE];
        currentPlayer = 'X';
        movesMade = 0;
        initializeBoard(); 
    }    

    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        return movesMade == SIZE * SIZE;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean placeMark(int row, int col) {
        if (board[row][col] == '-') {
            board[row][col] = currentPlayer;
            movesMade++;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Current Player: " + currentPlayer);
            printBoard();
            System.out.println("Enter row and column (1-3, space separated):");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && placeMark(row, col)) {
                if (checkWin()) {
                    System.out.println("Player " + currentPlayer + " won");
                    printBoard();
                    break;
                }
                if (isBoardFull()) {
                    System.out.println("tie");
                    printBoard();
                    break;
                }
                changePlayer();
            } else {
                System.out.println("Invalid position, try again");
            }
        }
        scanner.close();
    }

    
}
