package game.rules;

import static org.assertj.core.api.Assertions.*;

import game.rules.ShipSettings;
import game.fleet.ShipType;
import org.testng.annotations.Test;

import java.util.ArrayList;

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
