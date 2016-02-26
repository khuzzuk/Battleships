package game.fleet;

import game.board.fields.Field;

public class Ship implements PlaceableOnBoard {
    private ShipType type;
    private ShipFieldsList fields;

    public Ship(ShipType type) {
        fields = new ShipFieldsList(type.shipLength);
        this.type = type;
    }
    public void addFieldsFromBoard(Field... shipFields) throws IllegalArgumentException {
        if (shipFields.length==type.shipLength){
            for (Field f:shipFields) {
                fields.add(f);
                f.markShip();
            }
        }
        else throw new IllegalArgumentException("Ship has "+shipFields.length+" fields. This type should have "+type.shipLength+" fields.");
    }

    public boolean canBePlacedWith(PlaceableOnBoard otherShip){
        Ship toCompare = (Ship) otherShip;
        return fields.canBePlacedWith(toCompare.fields);
    }

    public boolean isPlacedOnBoard(){
        return fields.size()>0;
    }

    public boolean contains(Field field){
        return fields.contains(field);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (type != ship.type) return false;
        if (fields.size()==0 && ship.fields.size()==0) return true;
        return fields != null ? fields.equals(ship.fields) : ship.fields == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }

    public void clearFields() {
        fields = new ShipFieldsList(type.shipLength);
    }

    public boolean removeField(Field field) {
        return fields.remove(field);
    }

    public boolean isDestroyed() {
        return currentSize()==0;
    }
    public int currentSize(){
        return fields.size();
    }

    public ShipType shipType() {
        return type;
    }
}
