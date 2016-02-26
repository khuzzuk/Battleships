package game.board;

import game.board.fields.Field;
import game.board.fields.FieldsList;

import java.awt.*;

public class Board {
    public Dimension boardDimension;
    public BoardSize boardSize;
    final FieldsList fieldsList;

    public Board(BoardSize boardSize) {
        boardDimension = new Dimension(boardSize.size, boardSize.size);
        this.boardSize = boardSize;
        fieldsList = new FieldsList(boardSize.size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < boardDimension.width; y++) {
            sb.append("\n");
            sb.append("-------------------------------------------------");
            sb.append("\n");
            for (int x = 0; x < boardDimension.height; x++) {
                sb.append(fieldsList.print(new Field(x,y)));
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public boolean hasShip(Field field) {
        return fieldsList.hasShip(field);
    }

    public boolean hit(Field field) {
        return fieldsList.markHit(field);
    }

    public boolean miss(Field field) {
        return fieldsList.markMiss(field);
    }

    public Field get(Field field) {
        return fieldsList.get(field);
    }

    public FieldsList getFieldsList() {
        return fieldsList;
    }
}
