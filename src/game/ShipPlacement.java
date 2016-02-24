package game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by adrabik on 23.02.16.
 */
public class ShipPlacement {
    public static ArrayList<ShipType> startingShipList() {
        ArrayList<ShipType> shipTypesList = new ArrayList<>();
        shipTypesList.add(ShipType.BB);
        shipTypesList.add(ShipType.CA);
        shipTypesList.add(ShipType.CA);
        shipTypesList.add(ShipType.DD);
        shipTypesList.add(ShipType.DD);
        shipTypesList.add(ShipType.DD);
        shipTypesList.add(ShipType.SS);
        shipTypesList.add(ShipType.SS);
        shipTypesList.add(ShipType.SS);
        shipTypesList.add(ShipType.SS);
        return shipTypesList;
    }
}
