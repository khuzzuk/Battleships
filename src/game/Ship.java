package game;

import java.util.ArrayList;

/**
 * Created by adrabik on 19.02.16.
 */
public class Ship {
    ShipType type;
    ArrayList<Field> fields;

    public Ship(ShipType type) {
        this.type = type;
        fields = new ArrayList<>(type.shipLength);
    }
    public void addFieldsFromBoard(Field... shipFields) throws IllegalArgumentException {
        if (shipFields.length==type.shipLength){
            for (Field f:shipFields) {
                fields.add(f);
                f.markShip();
            }
        }
        else throw new IllegalArgumentException("Expected ship fields was "+shipFields.length+". Type should have "+type.ordinal()+" fields.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (type != ship.type) return false;
        return fields != null ? fields.equals(ship.fields) : ship.fields == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }
}
