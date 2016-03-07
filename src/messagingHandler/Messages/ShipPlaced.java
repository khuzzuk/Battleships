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

    public ShipPlaced(Player player, Ship ship, Point... shipPoints) {
        shipLocation = new Field[shipPoints.length];
        for (int i = 0; i < shipPoints.length; i++) {
            shipLocation[i] = new Field(shipPoints[i].x, shipPoints[i].y);
        }
        this.ship = ship;
        this.player = player;
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
