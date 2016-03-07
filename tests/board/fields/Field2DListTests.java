package board.fields;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Field2DListTests {
    @Test
    public void testPositionXAfterSort() throws Exception {
        // given
        List<Field> list = new ArrayList<>();
        for (int x=0;x<100;x++)
            list.add(new Field(x%10, x/10));
        Field2DList sortedList = new Field2DList(list);
        Field expectedField = new Field(5, 1);
        // when
        Field actualField = sortedList.fieldsXDim.get(15);
        // then
        assertThat(expectedField).isEqualTo(actualField);
    }
    @Test
    public void testPositionYAfterSort() throws Exception {
        // given
        List<Field> list = new ArrayList<>();
        for (int x=0;x<100;x++)
            list.add(new Field(x%10, x/10));
        Field2DList sortedList = new Field2DList(list);
        Field expectedField = new Field(1, 5);
        // when
        Field actualField = sortedList.fieldsYDim.get(15);
        // then
        assertThat(expectedField).isEqualTo(actualField);
    }
}
