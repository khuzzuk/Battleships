package game;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * Created by adrabik on 23.02.16.
 */
public class BoardTests {

    private static Board board;

    @BeforeMethod
    public static void setupBoard(){
        BoardSize boardSize = new BoardSize(10);
        board = new Board(boardSize);
    }
    @Test
    public static void testBoardFieldsNumber() {
        //given
        int oneHundred=100;
        //when
        int boardFieldsNumber = board.fieldsList.length;
        //then
        assertThat(boardFieldsNumber).isEqualTo(oneHundred);
    }
    @Test
    public static void testsFieldCoordinates11(){
        //given
        Point expectedCoordinates = new Point(1,1);
        //when
        Point fieldCoordinates = board.fieldsList[11].position;
        //then
        assertThat(fieldCoordinates).isEqualTo(expectedCoordinates);
    }
    @Test
    public static void testsFieldCoordinates78(){
        //given
        Point expectedCoordinates = new Point(7,8);
        //when
        Point fieldCoordinates = board.fieldsList[8*10+7].position;
        //then
        assertThat(fieldCoordinates).isEqualTo(expectedCoordinates);
    }

    @Test
    public void testsIfFieldIsEmpty() {
        //given
        Sign emptySign = Sign.EMPTY;
        //when
        Sign sign = board.fieldsList[1].sign;
        //then
        assertThat(sign).isEqualTo(emptySign);
    }
}
