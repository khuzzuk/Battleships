package messagingHandler.Actions;

import fleet.Ship;
import gameInterface.ShipPlacementWindow;
import messagingHandler.Messages.NextShipPlaceMessage;
import messagingHandler.Subscribers.Subscriber;

public class NextShipToPlaceAction implements Action {
    Ship ship;
    public NextShipToPlaceAction(NextShipPlaceMessage m) {
        ship=m.getShip();
    }

    @Override
    public void sendToSubscriber(Subscriber<?> sub) {
        if (sub.getClass()==ShipPlacementWindow.class)
            sendHelper((ShipPlacementWindow) sub);
    }
    private void sendHelper(ShipPlacementWindow sub){
        sub.notifySubscriber(this);
    }

    public Ship getShip() {
        return ship;
    }
}
