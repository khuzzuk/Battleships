package board;

import static org.assertj.core.api.Assertions.*;

import board.Board;
import board.BoardSize;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        int boardFieldsNumber = board.fieldsList.size();
        //then
        assertThat(boardFieldsNumber).isEqualTo(oneHundred);
    }

}
