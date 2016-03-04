package messagingHandler.Messages;

import game.Game;
import gameInterface.ShipPlacementWindow;
import messagingHandler.GameAdapter;
import player.Player;

public class PlayerStartsPlacingShips implements Message {
    public PlayerStartsPlacingShips(Game game, Player playerOne) {
        new ShipPlacementWindow(game, playerOne);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void send(GameAdapter adapter) {
        adapter.offer(this);
    }
}
