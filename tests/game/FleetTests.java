package game;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

public class FleetTests {
    @Test
    public void testsShipsStartingNumberOnFleet() {
        //given
        Fleet fleet = new Fleet(ShipPlacement.startingShipList());
        int expectedFleetSize = 10;
        //when
        int actualSize = fleet.numberOfShips();
        //then
        assertThat(expectedFleetSize).isEqualTo(actualSize);
    }

    @Test
    public void testsRemovingShipFromFleet() {
        //given
        Fleet fleet = new Fleet(ShipPlacement.startingShipList());
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
