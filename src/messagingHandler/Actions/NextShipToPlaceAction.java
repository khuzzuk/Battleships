package messagingHandler.Actions;

import fleet.Ship;
import gameInterface.ShipPlacementWindow;
import messagingHandler.Messages.NextShipPlaceMessage;

public class NextShipToPlaceAction implements Action {
    Ship ship;
    public NextShipToPlaceAction(NextShipPlaceMessage m) {
        ship=m.getShip();
    }

    @Override
    public void activateMessage() {

    }

    private void sendHelper(ShipPlacementWindow sub){
    }

    public Ship getShip() {
        return ship;
    }
}
