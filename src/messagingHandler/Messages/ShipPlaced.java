package messagingHandler.Messages;

import fleet.Ship;
import messagingHandler.GameAdapter;
import player.Player;

import java.awt.*;
import java.lang.reflect.Field;

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
}
