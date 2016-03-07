package messagingHandler.Messages;

import fleet.Ship;
import messagingHandler.GameAdapter;

public class NextShipPlaceMessage implements Message {
    Ship ship;
    public NextShipPlaceMessage(Ship nextShip) {
        ship=nextShip;
    }

    public Ship getShip() {
        return ship;
    }

}
