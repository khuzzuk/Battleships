package messagingHandler.Messages;

import game.Game;
import gameInterface.ShipPlacementWindow;
import messagingHandler.Actions.StartPlacingShipsAction;
import messagingHandler.GameAdapter;
import player.Player;

public class PlayerStartsPlacingShips implements Message {
    public PlayerStartsPlacingShips(Game game, Player playerOne) {
        new ShipPlacementWindow(game, playerOne);
    }

    @Override
    public void send(GameAdapter receiver) {
        receiver.receive(this);
    }
}
