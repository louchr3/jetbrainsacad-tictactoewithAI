package tictactoe;

import java.util.Arrays;

public class Board {

    private char[][] gameBoard;
    private static int filledCount;

    public Board() {
        gameBoard = new char[3][3];
        filledCount = 0;
        fillBoard();
    }

    public void addNew(int row, int col, char c) {
        gameBoard[row][col] = c;
        if (c != ' ') {
            filledCount++;
        }
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int k = 0; k < 3; k++) {
                System.out.print(gameBoard[i][k] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public char[][] getBoard() {
        return Arrays.copyOf(gameBoard, gameBoard.length);
    }

    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    private void fillBoard() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                gameBoard[i][k] = ' ';
            }
        }
    }

    public boolean checkResult(char player) {
        if (gameBoard[0][0] + gameBoard[0][1] + gameBoard[0][2] == player * 3 ||
                gameBoard[0][0] + gameBoard[1][0] + gameBoard[2][0] == player * 3 ||
                gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == player * 3 ||
                gameBoard[0][1] + gameBoard[1][1] + gameBoard[2][1] == player * 3 ||
                gameBoard[0][2] + gameBoard[1][2] + gameBoard[2][2] == player * 3 ||
                gameBoard[1][0] + gameBoard[1][1] + gameBoard[1][2] == player * 3 ||
                gameBoard[2][0] + gameBoard[2][1] + gameBoard[2][2] == player * 3 ||
                gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0] == player * 3) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for(int k = 0; k < 3; k++) {
                if (gameBoard[i][k] != ' ') {
                    count++;
                }
            }
        }
        return count == 9;
    }

    public int[] changeCoor(int col, int row) {
        int[] coor = new int[2];

        coor[0] = 3 - row;
        coor[1] = col - 1;

        return coor;
    }

    public StringBuilder getAvailableCoor() {
        StringBuilder availableCoor = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (gameBoard[i][k] == ' ') {
                    availableCoor.append(i + "," + k + " ");
                }
            }
        }
        return availableCoor;
    }

    public boolean checkWinningMove(int[] coor, char c) {
        int row = coor[0];
        int col = coor[1];

        if (row == 0 && col == 0) {
            return gameBoard[0][1] + gameBoard[0][2] == c * 2 ||
                    gameBoard[1][0] + gameBoard[1][1] == c * 2 || gameBoard[1][1] + gameBoard[2][2] == c * 2;
        }

        if (row == 0 && col == 1) {
            return gameBoard[0][0] + gameBoard[0][2] == c * 2 || gameBoard[1][1] + gameBoard[2][1] == c * 2;
        }

        if (row == 0 && col == 2) {
            return gameBoard[0][0] + gameBoard[0][1] == c * 2 ||
                    gameBoard[1][1] + gameBoard[2][0] == c * 2 ||
                    gameBoard[1][2] + gameBoard[2][2] == c * 2;
        }

        if (row == 1 && col == 0) {
            return gameBoard[1][1] + gameBoard[1][2] == c * 2 || gameBoard[0][0] + gameBoard[2][0] == c * 2;
        }

        if (row == 1 && col == 1) {
            return gameBoard[0][0] + gameBoard[2][2] == c * 2 ||
                    gameBoard[0][2] + gameBoard[2][0] == c * 2 ||
                    gameBoard[0][1] + gameBoard[2][1] == c * 2 ||
                    gameBoard[1][0] + gameBoard[1][2] == c * 2;
        }

        if (row == 1 && col == 2) {
            return gameBoard[0][2] + gameBoard[2][2] == c * 2 || gameBoard[1][0] + gameBoard[1][1] == c * 2;
        }

        if (row == 2 && col == 0) {
            return gameBoard[0][0] + gameBoard[1][0] == c * 2 ||
                    gameBoard[1][1] + gameBoard[0][2] == c * 2 ||
                    gameBoard[2][1] + gameBoard[2][2] == c * 2;
        }

        if (row == 2 && col == 1) {
            return gameBoard[0][1] + gameBoard[1][1] == c * 2 || gameBoard[2][0] + gameBoard[2][2] == c * 2;
        }

        if (row == 2 && col == 2) {
            return gameBoard[0][2] + gameBoard[1][2] == c * 2 ||
                    gameBoard[0][0] + gameBoard[1][1] == c * 2 ||
                    gameBoard[2][0] + gameBoard[2][1] == c * 2;
        }

        return false;
    }
}
