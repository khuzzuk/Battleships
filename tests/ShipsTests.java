import gameLogic.Ship;
import gameLogic.ShipType;
import org.testng.annotations.Test;

/**
 * Created by Adrian on 19.02.2016.
 */
@Test
public class ShipsTests {
    public void testBattleshipPlacement() {
        Ship ship = new Ship(ShipType.BB);
    }
}
