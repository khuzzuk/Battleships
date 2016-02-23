package game;

import java.util.ArrayList;

public class Fleet {
    ArrayList<Ship> shipsList;

    public Fleet() {
        ArrayList<ShipType> shipTypesList = ShipPlacement.startingShipList();
        shipsList = new ArrayList<>();
        for (ShipType st: shipTypesList) {
            shipsList.add(new Ship(st));
        }
    }
    public void placeShip(Ship ship, Field... shipFields){
        for (Ship sh : shipsList) {
            if (sh.equals(ship)){
                sh.addFieldsFromBoard(shipFields);
                break;
            }
        }
    }
}
