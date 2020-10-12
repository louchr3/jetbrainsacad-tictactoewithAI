package tictactoe;

import java.util.Random;

public class AIPlayer extends Player {

    private Difficulty difficulty;

    public AIPlayer(boolean isX, Difficulty difficulty) {
        super(isX);
        this.difficulty = difficulty;
    }

    @Override
    public int[] makeMove(Board board) {
        if (difficulty == Difficulty.EASY) {
            return easyMove(board);
        } else if (difficulty == Difficulty.MEDIUM) {
            return mediumMove(board);
        } else {
            return hardMove(board);
        }
    }

    private int[] easyMove(Board board) {
        char[][] gameBoard = board.getBoard();
        Random rand = new Random();
        int[] coor = new int[2];
        int row;
        int col;
        System.out.println("Making move level \"easy\"");
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (gameBoard[row][col] != ' ');
        coor[0] = row;
        coor[1] = col;
        return coor;
    }

    private int[] mediumMove(Board board) {
        StringBuilder availableCoor = board.getAvailableCoor();
        String[] coor = availableCoor.toString().split(" ");
        int[] newCoor = new int[2];
        int row;
        int col;

        System.out.println("Making move level \"medium\"");
        for (int i = 0; i < coor.length; i++) {
            String[] rowCol = coor[i].split(",");
            newCoor[0] = Integer.parseInt(rowCol[0]);
            newCoor[1] = Integer.parseInt(rowCol[1]);
            if (board.checkWinningMove(newCoor, isX ? 'X' : 'O')) {
                return newCoor;
            }
        }
        for (int i = 0; i < coor.length; i++) {
            String[] rowCol = coor[i].split(",");
            newCoor[0] = Integer.parseInt(rowCol[0]);
            newCoor[1] = Integer.parseInt(rowCol[1]);
            if (board.checkWinningMove(newCoor, isX ? 'O' : 'X')) {
                return newCoor;
            }
        }
        Random rand = new Random();
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (board.getBoard()[row][col] != ' ');
        newCoor[0] = row;
        newCoor[1] = col;
        return newCoor;
    }

    private int[] hardMove(Board board) {
        System.out.println("Making move level \"hard\"");
        char[][] gameBoard = board.getBoard();
        int bestScore = Integer.MIN_VALUE;
        int row = -1;
        int col = -1;
        int[] coor = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (gameBoard[i][k] == ' ') {
                    gameBoard[i][k] = isX ? 'X' : 'O';
                    board.setGameBoard(gameBoard);
                    int score = miniMax(board, false);
                    gameBoard[i][k] = ' ';
                    board.setGameBoard(gameBoard);
                    if (score > bestScore) {
                        bestScore = score;
                        row = i;
                        col = k;
                    }
                }
            }
        }
        coor[0] = row;
        coor[1] = col;
        return coor;
    }

    private int miniMax(Board board, boolean isMaximizing) {

        char player = isX ? 'X' : 'O';
        char otherPlayer = isX ? 'O' : 'X';
        char[][] gameBoard = board.getBoard();

        if (board.checkResult(player)) {
            return 10;
        }
        if (board.checkResult(otherPlayer)) {
            return -10;
        }
        if (board.isFull()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (gameBoard[i][k] == ' ') {
                        gameBoard[i][k] = player;
                        board.setGameBoard(gameBoard);
                        int score = miniMax(board, false);
                        gameBoard[i][k] = ' ';
                        board.setGameBoard(gameBoard);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (gameBoard[i][k] == ' ') {
                        gameBoard[i][k] = otherPlayer;
                        board.setGameBoard(gameBoard);
                        int score = miniMax(board, true);
                        gameBoard[i][k] = ' ';
                        board.setGameBoard(gameBoard);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
