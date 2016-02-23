package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by adrabik on 22.02.16.
 */
public class Board {
    public static Dimension boardDimension;
    Field[] fieldsList;

    public Board(BoardSize boardSize) {
        boardDimension = new Dimension(boardSize.size, boardSize.size);
        fieldsList = new Field[boardDimension.width*boardDimension.height];
        for (int x = 0; x < fieldsList.length; x++) {
            fieldsList[x] = new Field((x)%10, x/10, Sign.EMPTY);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < fieldsList.length; x++) {
            if (fieldsList[x].position.x==0) {
                sb.append("\n");
                sb.append("-------------------------------------------------");
                sb.append("\n");
            }
            if(fieldsList[x].toString().equals(Sign.EMPTY.name())) {
                sb.append(x);
            } else {
                sb.append(fieldsList[x]);
            }

            sb.append(" | ");
        }
        return sb.toString();
    }

    public int countMoves() {
        return 0;
    }

    public void shot(Point field) {
    }
}
