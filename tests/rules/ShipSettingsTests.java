package rules;

import fleet.ShipType;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ShipSettingsTests {
    @Test
    public void testNumberOfStartingShips() {
        //given
        ArrayList<ShipType> shipTypes = ShipSettings.startingShipList();
        int expectedNumber = 10;
        //when
        int actualNumberOfShips = shipTypes.size();
        //then
        assertThat(actualNumberOfShips).isEqualTo(expectedNumber);
    }
}
