package game;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adrabik on 25.02.16.
 */
public class ShipTests {
    @Test
    public static void testPlaceShipOnOtherShip() {
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
    public static void testPlaceShipAdjacentToOtherShip() {
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
