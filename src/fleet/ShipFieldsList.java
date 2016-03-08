package fleet;

import board.fields.Field;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ShipFieldsList implements PlaceableOnBoard {
    private Set<Field> fields;
    private Set<Field> adjacentFields;

    public ShipFieldsList(int shipLength) {
        fields = new HashSet<>();
        adjacentFields = new HashSet<>();
    }
    public void add(Field field){
        fields.add(field);
    }
    public void addAdjacentFields(Set<Field> fields){
        adjacentFields=fields;
    }

    public boolean canBePlacedWith(PlaceableOnBoard otherList){
        ShipFieldsList otherFieldsList = (ShipFieldsList) otherList;
        for (Field f:otherFieldsList.fields) {
            if (fields.contains(f)) return false;
        }
        return (Collections.disjoint(fields, otherFieldsList.fields) && Collections.disjoint(fields,otherFieldsList.adjacentFields));
    }

    @Override
    public boolean isPlacedOnBoard() {
        return !fields.isEmpty();
    }

    public int size() {
        return fields.size();
    }

    public boolean contains(Field field) {
        return fields.contains(field);
    }

    public boolean remove(Field field) {
        return fields.remove(field);
    }
}
