package board.fields;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Field2DListTests {
    private static Field2DList field2DList;
    @BeforeTest
    public static void prepareList(){
        List<Field> list = new ArrayList<>(100);
        for (int x=0;x<100;x++)
            list.add(new Field(x%10, x/10));
        field2DList = new Field2DList(list);
    }
    @Test
    public void testPositionXAfterSort() throws Exception {
        // given
        Field expectedField = new Field(5, 1);
        // when
        Field actualField = field2DList.fieldsXDim.get(15);
        // then
        assertThat(expectedField).isEqualTo(actualField);
    }
    @Test
    public void testPositionYAfterSort() throws Exception {
        // given
        Field expectedField = new Field(1, 5);
        // when
        Field actualField = field2DList.fieldsYDim.get(15);
        // then
        assertThat(expectedField).isEqualTo(actualField);
    }

    @Test
    public void testsWhatFieldIsOnRight() throws Exception {
        // given
        Field baseField = new Field(5,5);
        Field expectedField = new Field(6,5);
        // when
        Field fieldOnRight = field2DList.getAdjacentRight(baseField);
        // then
        assertThat(fieldOnRight).isEqualTo(expectedField);
    }
    @Test
    public void testsWhatFieldIsOnLeft() throws Exception {
        // given
        Field baseField = new Field(3,8);
        Field expectedField = new Field(2,8);
        // when
        Field fieldOnRight = field2DList.getAdjacentLeft(baseField);
        // then
        assertThat(fieldOnRight).isEqualTo(expectedField);
    }
    @Test
    public void testsWhatFieldIsAtLowerY() throws Exception {
        // given
        Field baseField = new Field(3,8);
        Field expectedField = new Field(3,7);
        // when
        Field fieldOnRight = field2DList.getAdjacentUp(baseField);
        // then
        assertThat(fieldOnRight).isEqualTo(expectedField);
    }
    @Test
    public void testsWhatFieldIsAtHigherY() throws Exception {
        // given
        Field baseField = new Field(4,5);
        Field expectedField = new Field(4,6);
        // when
        Field fieldOnRight = field2DList.getAdjacentDown(baseField);
        // then
        assertThat(fieldOnRight).isEqualTo(expectedField);
    }
    @Test
    public void testsWhatFieldIsOutsideBoardOnRight() throws Exception {
        // given
        Field baseField = new Field(9,5);
        Field expectedField = new Field(-1,-1);
        // when
        Field fieldOnRight = field2DList.getAdjacentRight(baseField);
        // then
        assertThat(fieldOnRight).isEqualTo(expectedField);
    }
    @Test
    public void testsWhatFieldIsOutsideBoardOnLeft() throws Exception {
        // given
        Field baseField = new Field(0,6);
        Field expectedField = new Field(-1,-1);
        // when
        Field fieldOnRight = field2DList.getAdjacentLeft(baseField);
        // then
        assertThat(fieldOnRight).isEqualTo(expectedField);
    }
    @Test
    public void testsWhatFieldIsOutsideBoardUp() throws Exception {
        // given
        Field baseField = new Field(6,0);
        Field expectedField = new Field(-1,-1);
        // when
        Field fieldOnRight = field2DList.getAdjacentUp(baseField);
        // then
        assertThat(fieldOnRight).isEqualTo(expectedField);
    }
    @Test
    public void testsWhatFieldIsOutsideBoardDown() throws Exception {
        // given
        Field baseField = new Field(6,9);
        Field expectedField = new Field(-1,-1);
        // when
        Field fieldOnRight = field2DList.getAdjacentDown(baseField);
        // then
        assertThat(fieldOnRight).isEqualTo(expectedField);
    }
}
