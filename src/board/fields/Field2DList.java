package board.fields;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Field2DList extends AbstractList<Field> implements List<Field>, SortedSet<Field> {
    List<Field> fieldsXDim;
    List<Field> fieldsYDim;
    int dimension;

    public Field2DList(List<Field> fields) {
        this.fieldsXDim = new ArrayList<>();
        fieldsXDim.addAll(fields);
        this.fieldsYDim = new ArrayList<>();
        fieldsYDim.addAll(fields);
        Comparator<Field> comparatorX = Comparator.comparing(Field::getPositionY).thenComparing(Field::getPositionX);
        Comparator<Field> comparatorY = Comparator.comparing(Field::getPositionX).thenComparing(Field::getPositionY);
        fieldsXDim.sort(comparatorX);
        fieldsYDim.sort(comparatorY);
        dimension=(int)Math.sqrt(fields.size());
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

    public List<Field> getAdjacent(Field... toFields){
        List<Field> list = new ArrayList<>();
        for (Field f : toFields){
            list.addAll(getAdjacent(f));
        }
        list.removeAll(Arrays.asList(toFields));
        return list;
    }
    List<Field> getAdjacent(Field toField){
        List<Field> list = new ArrayList<>(4);
        list.add(getAdjacentUp(toField));
        list.add(getAdjacentRight(toField));
        list.add(getAdjacentDown(toField));
        list.add(getAdjacentLeft(toField));
        list.remove(new Field(-1,-1));
        return list;
    }
    Field getAdjacentRight(Field field){
        if (field.getPositionX()==dimension-1)
            return new Field(-1,-1);
        int position = field.getPositionX()+dimension*field.getPositionY()+1;
        return fieldsXDim.get(position);
    }
    Field getAdjacentLeft(Field field){
        if (field.getPositionX()==0)
            return new Field(-1,-1);
        int position = field.getPositionX()+dimension*field.getPositionY()-1;
        return fieldsXDim.get(position);
    }
    Field getAdjacentUp(Field field){
        if (field.getPositionY()==0)
            return new Field(-1,-1);
        int position = field.getPositionY()+dimension*field.getPositionX()-1;
        return fieldsYDim.get(position);
    }
    Field getAdjacentDown(Field field){
        if (field.getPositionY()==dimension-1)
            return new Field(-1,-1);
        int position = field.getPositionY()+dimension*field.getPositionX()+1;
        return fieldsYDim.get(position);
    }

    @Override
    public boolean contains(Object o){
        if (o.getClass()!= Field.class)
        return false;
        return fieldsXDim.contains(o);
    }

    @Override
    public boolean add(Field field) {
        throw new UnsupportedOperationException("List is unmodifiable");
    }

    @Override
    public int size() {
        return fieldsXDim.size();
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
        return fieldsXDim.iterator();
    }

    @Override
    public Field get(int index) {
        return fieldsXDim.get(index);
    }

    @Override
    public void sort(Comparator<? super Field> c) {
        throw new UnsupportedOperationException("Collection is sorted");
    }

    @Override
    public Field last() {
        return fieldsXDim.get(fieldsXDim.size()-1);
    }

    @Override
    public Field first() {
        if (fieldsXDim.size()==0) return null;
        return fieldsXDim.get(0);
    }

    @Override
    public SortedSet<Field> tailSet(Field fromElement) {
        return null;
    }

    @Override
    public SortedSet<Field> headSet(Field toElement) {
        return null;
    }

    @Override
    public SortedSet<Field> subSet(Field fromElement, Field toElement) {
        return null;
    }

    @Override
    public Comparator<? super Field> comparator() {
        return Comparator.comparing(Field::getPositionX).thenComparing(Field::getPositionY);
    }

    @Override
    public Spliterator<Field> spliterator() {
        return null;
    }
}
