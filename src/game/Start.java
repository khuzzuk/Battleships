package game;

import game.board.BoardSize;

public class Start {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new Game(new BoardSize(10)));
    }
}
