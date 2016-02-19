import GameLogic.Board;
import static org.assertj.core.api.Assertions.*;

import GameLogic.Field;
import GameLogic.Mark;
import GameLogic.Player;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * Created by Adrian on 18.02.2016.
 */
@Test
public class BoardTests {

    private static Board board;

    public static void testEmptyBoard(){
        // given
        int expected = 0;
        // when
        int filledFieldsNumber = board.countMoves();
        // then
        assertThat(filledFieldsNumber).isEqualTo(expected);
    }
    public static void testOneShot(){
        // given
        Dimension field = new Dimension(1,1);
        board.shot(field);
        Mark expected = Mark.HITTED;

        // when
        Mark fieldStatus = board.checkField(field);

        // then
        assertThat(fieldStatus).isEqualTo(expected);

    }

    @BeforeMethod
    public void setUp() {
        board = new Board();
    }
}
