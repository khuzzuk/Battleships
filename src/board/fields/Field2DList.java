package board.fields;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Field2DList extends AbstractList<Field> {
    List<Field> fieldsXDim;
    List<Field> fieldsYDim;

    public Field2DList(List<Field> fields) {
        this.fieldsXDim = new ArrayList<>();
        fieldsXDim.addAll(fields);
        this.fieldsYDim = new ArrayList<>();
        fieldsYDim.addAll(fields);
        Comparator<Field> comparatorX = Comparator.comparing(Field::getPositionY).thenComparing(Field::getPositionX);
        Comparator<Field> comparatorY = Comparator.comparing(Field::getPositionX).thenComparing(Field::getPositionY);
        fieldsXDim.sort(comparatorX);
        fieldsYDim.sort(comparatorY);
    }
    void sortWithLambda(){
        Collections.shuffle(fieldsXDim);
        Collections.shuffle(fieldsYDim);
        fieldsXDim.sort((field1, field2)->Field.compareToX(field1,field2));
        fieldsXDim.sort((field1, field2)->Field.compareToY(field1,field2));
    }
    void sortWithInnerClass(){
        Collections.shuffle(fieldsXDim);
        fieldsXDim.sort(new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.position.x-o2.position.x;
            }
        });
        fieldsXDim.sort(new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.position.y-o2.position.y;
            }
        });
    }

    private void lambdaComparing() {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean removeIf(Predicate<? super Field> filter) {
        return false;
    }

    @Override
    public void forEach(Consumer<? super Field> action) {
throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Field> iterator() {
        return null;
    }

    @Override
    public Field get(int index) {
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<Field> operator) {

    }

    @Override
    public void sort(Comparator<? super Field> c) {

    }
}
