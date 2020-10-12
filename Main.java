package tictactoe;
import java.util.Scanner;

/*
Tictactoe with AI
Easy AI - Choose a random move based in empty cells.
Medium AI - Priorities the move that can generate a win condition. If there is no winning move, it checks if the
opponent is about to win and block the opponent's winning move. If there is no winning move for both players, it will
generate a random move.
Hard AI - Implemented using minimax algorithm. The match outcome is either the human player lost or a draw.
 */

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Player player1;
    private static Player player2;

    public static void main(String[] args) {
        int[] coor;
        String command;
        String xPlayer;
        String oPlayer;

        do {
            boolean xTurn = true;
            Board board = new Board();
            System.out.print("Input command: ");
            String[] input = scanner.nextLine().split(" ");
            command = input[0];
            if (!"exit".equals(command) && input.length < 3) {
                System.out.println("Bad parameters!");
                continue;
            }
            if ("exit".equals(command)) {
                break;
            }

            xPlayer = input[1];
            oPlayer = input[2];

            if (!"user".equals(xPlayer) & !"easy".equals(xPlayer) & !"medium".equals(xPlayer) & !"hard".equals(xPlayer)) {
                System.out.println("Bad parameters!");
                continue;
            }

            if (!"user".equals(oPlayer) & !"easy".equals(oPlayer) & !"medium".equals(oPlayer) & !"hard".equals(oPlayer)) {
                System.out.println("Bad parameters!");
                continue;
            }

            createPlayerObject(input[1], input[2]);
            board.printBoard();
            while (true) {
                if (xTurn) {
                    coor = player1.makeMove(board);
                } else {
                    coor = player2.makeMove(board);
                }
                if (coor != null) {
                    board.addNew(coor[0], coor[1], xTurn ? 'X' : 'O');
                    board.printBoard();
                } else {
                    continue;
                }

                if (board.checkResult(xTurn ? 'X' : 'O')) {
                    if (xTurn) {
                        System.out.println("X wins");
                    } else {
                        System.out.println("O wins");
                    }
                    break;
                }
                if (board.isFull()) {
                    System.out.println("Draw");
                    break;
                }
                xTurn = !xTurn;
            }
        } while (true);
    }

    private static void createPlayerObject(String p1, String p2) {

        if ("user".equals(p1)) {
            player1 = new HumanPlayer(true);
        } else if ("easy".equals(p1)) {
            player1 = new AIPlayer(true, Difficulty.EASY);
        } else if ("medium".equals(p1)) {
            player1 = new AIPlayer(true, Difficulty.MEDIUM);
        } else {
            player1 = new AIPlayer(true, Difficulty.HARD);
        }

        if ("user".equals(p2)) {
            player2 = new HumanPlayer(false);
        } else if ("easy".equals(p2)) {
            player2 = new AIPlayer(false, Difficulty.EASY);
        } else if ("medium".equals(p2)) {
            player2 = new AIPlayer(false, Difficulty.MEDIUM);
        } else {
            player2 = new AIPlayer(false, Difficulty.HARD);
        }
    }
}
