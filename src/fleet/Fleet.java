package fleet;

import board.fields.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Fleet {
    ArrayList<Ship> shipsList;

    public Fleet(List<ShipType> ships) {
        shipsList = new ArrayList<>();
        for (ShipType st: ships) {
            shipsList.add(new Ship(st));
        }
    }
    public boolean placeShip(Ship ship, List<Field> shipFields, Set<Field> adjacentFields){
            for (int x=0; x<shipsList.size(); x++) {
                if (shipsList.get(x).equals(ship)){
                    assignFields(ship, shipFields, adjacentFields);
                    shipsList.set(x, ship);
                    return true;
                }
            }
        return false;
    }
    public boolean shootShip (Field field){
        Ship ship = findShipOnField(field);
        if (ship!=null){
            ship.removeField(field);
            if (ship.isDestroyed()){
                remove(ship);
            }
            return true;
        }
        return false;
    }

    private boolean remove(Ship ship) {
        return shipsList.remove(ship);
    }

    private Ship findShipOnField(Field field) {
        for (Ship s:shipsList) {
            if (s.contains(field)) return s;
        }
        return null;
    }

    public boolean canBePlacedOnBoard(Ship ship, Field... fields){
        assignFields(ship, fields); // TODO: 2/26/2016 Make method canBePlacedWith of Ship class to accept also fields, not ship only
        for (Ship s : shipsList) {
            if (!ship.canBePlacedWith(s)) {
                ship.clearFields();
                return false;
            }
        }
        ship.clearFields();
        return true;
    }

    private void assignFields(Ship ship, Field[] fields) {
        ship.addFieldsFromBoard(fields);
    }
    private void assignFields(Ship ship, List<Field> fields, Set<Field> adjacentFields){
        ship.addFieldsFromBoard(fields);
        ship.addAdjacentFields(adjacentFields);;
    }

    public Ship getFirstFree(){
        for (Ship s : shipsList) {
            if (!s.isPlacedOnBoard()) return s;
        }
        return null;
    }

    public int numberOfShips() {
        return shipsList.size();
    }
}
