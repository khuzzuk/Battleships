package messagingHandler.Actions;

import board.fields.Field;
import fleet.Ship;
import game.Game;
import messagingHandler.Messages.ShipPlaced;
import messagingHandler.Subscribers.Subscriber;
import player.Player;

public class PlaceShipOnBoardAction extends GeneralAction implements Action {
    private Ship ship;
    private Player player;
    private Field[] fields;

    public PlaceShipOnBoardAction(Ship ship, Player player, Field... fields) {
        this.ship = ship;
        this.player = player;
        this.fields = fields;
    }

    public PlaceShipOnBoardAction(ShipPlaced m) {
        ship = m.getShip();
        player = m.getPlayer();
        fields = m.getFields();
    }

    @Override
    public void sendToSubscriber(Subscriber<?> sub) {
        if (sub.getClass()==Game.class)
            sendHelper((Game) sub);
    }
    private void sendHelper(Game sub){
        sub.notifySubscriber(this);
    }

    public Ship getShip() {
        return ship;
    }

    public Player getPlayer() {
        return player;
    }

    public Field[] getFields() {
        return fields;
    }
}
