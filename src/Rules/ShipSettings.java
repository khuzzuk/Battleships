package rules;

import fleet.ShipType;

import java.util.ArrayList;

public class ShipSettings {
    public static ArrayList<ShipType> startingShipList() {
        ArrayList<ShipType> shipTypesList = new ArrayList<>();
        shipTypesList.add(ShipType.BB);
        shipTypesList.add(ShipType.CA);
        /*shipTypesList.add(ShipType.CA);
        shipTypesList.add(ShipType.DD);
        shipTypesList.add(ShipType.DD);
        shipTypesList.add(ShipType.DD);
        shipTypesList.add(ShipType.SS);
        shipTypesList.add(ShipType.SS);
        shipTypesList.add(ShipType.SS);
        shipTypesList.add(ShipType.SS);*/
        return shipTypesList;
    }
}
