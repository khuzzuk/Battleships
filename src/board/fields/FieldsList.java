package board.fields;

import board.BoardSize;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class FieldsList {
    private Set<Field> fieldList;

    public FieldsList(BoardSize boardSize) {
        fieldList = new HashSet<>(boardSize.size);
        for (int x = 0; x < Math.pow(boardSize.size,2); x++) {
            fieldList.add(new Field(x%boardSize.size, x/boardSize.size));
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
        throw new NoSuchElementException("Can't find field " + field);
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
