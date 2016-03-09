package board;

public class BoardSize {

    public final int size;

    public BoardSize(int boardSize) {
        this.size = boardSize;
    }

    public BoardSize(BoardSize boardSize) {
        size = boardSize.size;
    }
}
