package game.fleet;

import static org.assertj.core.api.Assertions.*;

import game.board.fields.Field;
import game.fleet.Fleet;
import game.fleet.Ship;
import game.rules.ShipSettings;
import game.fleet.ShipType;
import org.testng.annotations.Test;

public class FleetTests {
    @Test
    public void testsShipsStartingNumberOnFleet() {
        //given
        Fleet fleet = new Fleet(ShipSettings.startingShipList());
        int expectedFleetSize = 10;
        //when
        int actualSize = fleet.numberOfShips();
        //then
        assertThat(expectedFleetSize).isEqualTo(actualSize);
    }

    @Test
    public void testsRemovingShipFromFleet() {
        //given
        Fleet fleet = new Fleet(ShipSettings.startingShipList());
        Ship ship = new Ship(ShipType.SS);
        fleet.placeShip(ship, new Field(1,1));
        fleet.shootShip(new Field(1,1));
        int expectedFleetSize = 9;
        //when
        int actualSize = fleet.numberOfShips();
        //then
        assertThat(expectedFleetSize).isEqualTo(actualSize);
    }
}
