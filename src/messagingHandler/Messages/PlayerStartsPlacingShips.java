package messagingHandler.Messages;

import game.Game;
import gameInterface.ShipPlacementWindow;
import messagingHandler.Actions.Action;
import messagingHandler.GameAdapter;
import player.Player;

@PriorityMessage
public class PlayerStartsPlacingShips implements Message, Action {
    Player player;
    Game game;
    public PlayerStartsPlacingShips(Game game, Player player) {
        this.player=player;
        this.game=game;
    }

    @Override
    public void activateMessage() {
        new ShipPlacementWindow(game, player);
    }
}
