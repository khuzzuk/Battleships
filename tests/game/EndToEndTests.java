package game;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

/**
 * Created by adrabik on 23.02.16.
 */
public class EndToEndTests {
    @Test
    public void testPlaceShipsOnBoard() {
        //given
        Player player = new Player(new BoardSize(10));
        Ship ship = new Ship(ShipType.SS);
        Field field = player.board.fieldsList[25];
        player.fleet.placeShip(ship,field);
        Sign expectedSign = Sign.SHIP;
        //when
        Sign signOnBoard = player.board.fieldsList[25].sign;
        //then
        assertThat(signOnBoard).isEqualTo(expectedSign);
    }
}
