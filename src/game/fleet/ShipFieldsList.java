package game.fleet;

import game.board.fields.Field;

import java.util.HashSet;
import java.util.Set;

public class ShipFieldsList implements PlaceableOnBoard {
    private Set<Field> fields;

    public ShipFieldsList(int shipLength) {
        fields = new HashSet<>();
    }
    public void add(Field field){
        fields.add(field);
    }

    public boolean canBePlacedWith(PlaceableOnBoard otherList){
        ShipFieldsList otherFieldsList = (ShipFieldsList) otherList;
        for (Field f:otherFieldsList.fields) {
            if (fields.contains(f)) return false;
        }
        return true;
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
