package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by adrabik on 22.02.16.
 */
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

        for (int x = 0; x < boardDimension.width; x++) {
            sb.append("\n");
            sb.append("-------------------------------------------------");
            sb.append("\n");
            for (int y = 0; y < boardDimension.height; y++) {
                sb.append(fieldsList.get(new Field(x,y)));
                sb.append(" | ");
            }
        }
        return sb.toString();
    }



    public void shot(Point field) {
    }
}
