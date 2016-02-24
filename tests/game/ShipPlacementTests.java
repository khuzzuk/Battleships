package game;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ShipPlacementTests {
    @Test
    public void testNumberOfStartingShips() {
        //given
        ArrayList<ShipType> shipTypes = ShipPlacement.startingShipList();
        int expectedNumber = 10;
        //when
        int actualNumberOfShips = shipTypes.size();
        //then
        Assertions.assertThat(actualNumberOfShips).isEqualTo(expectedNumber);
    }
}