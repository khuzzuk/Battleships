package messagingHandler.Actions;

import gameInterface.ShipPlacementWindow;
import messagingHandler.Subscribers.Subscriber;

public class StartPlacingShipsAction implements Action {
    @Override
    public void sendToSubscriber(Subscriber<?> sub) {
        if (sub.getClass()== ShipPlacementWindow.class)
            sendHelper((ShipPlacementWindow) sub);
    }
    private void sendHelper(ShipPlacementWindow sub){
        sub.notifySubscriber(this);
    }
}
