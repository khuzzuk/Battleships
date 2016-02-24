package game;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

import java.awt.*;
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
        assertThat(actualNumberOfShips).isEqualTo(expectedNumber);
    }

    @Test
    public void testPlaceShipOnOtherShip() {
        //given
        Ship firstShip = new Ship(ShipType.DD);
        Ship secondShip = new Ship(ShipType.DD);
        firstShip.addFieldsFromBoard(new Field(0,1,Sign.EMPTY), new Field(1,1,Sign.EMPTY));
        secondShip.addFieldsFromBoard(new Field(1,1,Sign.EMPTY), new Field(2,1,Sign.EMPTY));
        //when
        boolean canBePlaced = firstShip.canBePlacedWith(secondShip);
        //then
        assertThat(canBePlaced).isFalse();
    }
    @Test
    public void testPlaceShipAdjacentToOtherShip() {
        //given
        Ship firstShip = new Ship(ShipType.DD);
        Ship secondShip = new Ship(ShipType.DD);
        firstShip.addFieldsFromBoard(new Field(0,1,Sign.EMPTY), new Field(1,1,Sign.EMPTY));
        secondShip.addFieldsFromBoard(new Field(2,1,Sign.EMPTY), new Field(3,1,Sign.EMPTY));
        //when
        boolean canBePlaced = firstShip.canBePlacedWith(secondShip);
        //then
        assertThat(canBePlaced).isTrue();
    }
}
