package fleet;

import board.fields.Field;
import org.testng.annotations.Test;
import rules.ShipSettings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
        List<Field> fields = new ArrayList<>();
        fields.add(new Field(1,1));
        fleet.placeShip(ship, fields, new HashSet<>());
        fleet.shootShip(new Field(1,1));
        int expectedFleetSize = 9;
        //when
        int actualSize = fleet.numberOfShips();
        //then
        assertThat(expectedFleetSize).isEqualTo(actualSize);
    }
}
