package messagingHandler.Messages;

import board.fields.Field;
import fleet.Ship;
import messagingHandler.GameAdapter;
import player.Player;

import java.awt.*;

public class ShipPlaced implements Message {
    private Field[] shipLocation;
    private Ship ship;
    private Player player;

    public ShipPlaced(Player player, Ship ship, Field... shipLocation) {
        this.shipLocation = shipLocation;
        this.ship = ship;
        this.player = player;
    }

    @Override
    public void send(GameAdapter receiver) {
        receiver.receive(this);
    }

    public Ship getShip() {
        return ship;
    }

    public Player getPlayer() {
        return player;
    }

    public Field[] getFields() {
        return shipLocation;
    }
}
