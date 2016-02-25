package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Board {
    public Dimension boardDimension;
    final FieldsList fieldsList;

    public Board(BoardSize boardSize) {
        boardDimension = new Dimension(boardSize.size, boardSize.size);
        fieldsList = new FieldsList(boardSize.size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Field currentField;

        for (int y = 0; y < boardDimension.width; y++) {
            sb.append("\n");
            sb.append("-------------------------------------------------");
            sb.append("\n");
            for (int x = 0; x < boardDimension.height; x++) {
                sb.append(fieldsList.get(new Field(x,y)));
                sb.append(" | ");
            }
        }
        return sb.toString();
    }





    public boolean shot(Field field) {
        Field shootedField = fieldsList.get(field);
        return shootedField.shoot();
    }

    public boolean hasShip(Field field) {
        return fieldsList.get(field).hasShip();
    }

    public boolean hit(Field field) {
        return fieldsList.get(field).markHit();
    }

    public boolean miss(Field field) {
        return fieldsList.get(field).markMiss();
    }
}
