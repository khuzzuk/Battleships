package fleet;

import board.fields.Sign;
import board.fields.Field;
import fleet.Ship;
import fleet.ShipType;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShipTests {
    @Test
    public static void testPlaceShipOnOtherShip() {
        //given
        Ship firstShip = new Ship(ShipType.DD);
        Ship secondShip = new Ship(ShipType.DD);
        firstShip.addFieldsFromBoard(new Field(0,1, Sign.EMPTY), new Field(1,1,Sign.EMPTY));
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

    @Test
    public void testsReducedShipSize() {
        //given
        int expectedLengthOfThree = 3;
        Ship ship = new Ship(ShipType.BB);
        ship.addFieldsFromBoard(new Field(1,1), new Field(1,2), new Field(1,3), new Field(1,4));
        ship.removeField(new Field(1,1));
        //when
        int actualShipLength = ship.currentSize();
        //then
        assertThat(expectedLengthOfThree).isEqualTo(actualShipLength);
    }
    @Test
    public void testsDestroyingShip() {
        //given
        Ship ship = new Ship(ShipType.BB);
        ship.addFieldsFromBoard(new Field(1,1), new Field(1,2), new Field(1,3), new Field(1,4));
        ship.removeField(new Field(1,1));
        ship.removeField(new Field(1,2));
        ship.removeField(new Field(1,3));
        ship.removeField(new Field(1,4));
        //when
        boolean shipDestroyed = ship.isDestroyed();
        //then
        assertThat(shipDestroyed).isTrue();
    }
}
