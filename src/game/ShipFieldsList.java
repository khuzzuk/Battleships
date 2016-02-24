package game;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by adrabik on 24.02.16.
 */
public class ShipFieldsList implements PlaceableOnBoard {
    private Set<Field> fields;

    public ShipFieldsList(int shipLength) {
        fields = new TreeSet<>();
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
        if (fields.isEmpty()) return false;
        return true;
    }

    public int size() {
        return fields.size();
    }
}
