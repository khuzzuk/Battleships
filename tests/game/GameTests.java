package game;

import board.BoardSize;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import player.Player;
import player.PlayerNumber;

public class GameTests {
    @Test
    public void testStartingPlayer() throws Exception {
        BoardSize boardSize = new BoardSize(10);
        Game game = new Game();
        game.setupGame(boardSize);
        Player player = new Player(boardSize, new PlayerNumber(1));

        boolean playerOneStarts = player.equals(game.currentPlayer);

        Assertions.assertThat(playerOneStarts).isTrue();
    }
}
