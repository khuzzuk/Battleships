package board.fields;

import board.fields.Field;
import board.fields.Sign;
import org.testng.annotations.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Adrian_Drabik on 2/26/2016.
 */
public class FieldTests {
    @Test
    public static void testsFieldCoordinates11(){
        //given
        Point expectedCoordinates = new Point(1,1);
        Field field = new Field(1,1);
        //when
        Point fieldCoordinates = field.position;
        //then
        assertThat(fieldCoordinates).isEqualTo(expectedCoordinates);
    }

    @Test
    public static void testsFieldCoordinates78(){
        //given
        Point expectedCoordinates = new Point(7,8);
        Field field = new Field(7,8);
        //when
        Point fieldCoordinates = field.position;
        //then
        assertThat(fieldCoordinates).isEqualTo(expectedCoordinates);
    }

    @Test
    public static void testsIfFieldIsEmpty() {
        //given
        Sign emptySign = Sign.EMPTY;
        Field field = new Field(1,1);
        //when
        Sign sign = field.sign;
        //then
        assertThat(sign).isEqualTo(emptySign);
    }
}
