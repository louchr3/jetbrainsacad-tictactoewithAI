package tictactoe;

public abstract class Player {

    protected boolean isX;

    public Player(boolean isX) {
        this.isX = isX;
    }

    public abstract int[] makeMove(Board board);
}
