package board.fields;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by adrabik on 24.02.16.
 */
public class FieldsList {
    private Set<Field> fieldList;

    public FieldsList(int size) {
        fieldList = new HashSet<>();
        for (int x = 0; x < size * size; x++) {
            fieldList.add(new Field(x%10, x/10));
        }
    }

    public int size() {
        return fieldList.size();
    }

    public Field get(Field field) {
        Iterator<Field> iterator = fieldList.iterator();
        Field currentField;
        while(iterator.hasNext()) {
            currentField = iterator.next();
            if(currentField.equals(field)) {
                return currentField;
            }
        }
        throw new NoSuchElementException("Nie znaleziono pola");
    }
    public boolean hasShip(Field field){
        return get(field).hasShip();
    }
    public boolean markHit(Field field){
        return get(field).markHit();
    }

    public boolean markMiss(Field field) {
        return get(field).markMiss();
    }

    public String print(Field field) {
        return get(field).toString();
    }
}
