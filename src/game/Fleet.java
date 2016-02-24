package game;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    ArrayList<Ship> shipsList;

    public Fleet(List<ShipType> ships) {
        shipsList = new ArrayList<>();
        for (ShipType st: ships) {
            shipsList.add(new Ship(st));
        }
    }
    public boolean placeShip(Ship ship, Field... shipFields){
        if (canBePlacedOnBoard(ship, shipFields)){
            for (int x=0; x<shipsList.size(); x++) {
                if (shipsList.get(x).equals(ship)){
                    shipsList.set(x, ship);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean canBePlacedOnBoard(Ship ship, Field... fields){
        assignFields(ship, fields);
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

    public Ship getFirstFree(){
        for (Ship s : shipsList) {
            if (!s.isPlacedOnBoard()) return s;
        }
        return null;
    }
}
