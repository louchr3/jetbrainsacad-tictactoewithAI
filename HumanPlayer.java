package tictactoe;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(boolean isX) {
        super(isX);
    }

    @Override
    public int[] makeMove(Board board) {
        char[][] gameBoard = board.getBoard();
        System.out.print("Enter the coordinates: ");
        int[] coor = new int[2];
        String input = scanner.nextLine();
        String[] num = input.split(" ");
        try {
            coor[0] = Integer.parseInt(num[0]);
            coor[1] = Integer.parseInt(num[1]);
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return null;
        }

        if (coor[0] < 1 || coor[0] > 3 || coor[1] < 1 || coor[1] > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return null;
        }

        int[] newCoor = board.changeCoor(coor[0], coor[1]);

        if (gameBoard[newCoor[0]][newCoor[1]] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return null;
        }

        return newCoor;
    }
}
