package game;

import game.board.BoardSize;
import game.player.Player;
import game.player.PlayerNumber;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class GameTests {
    @Test
    public void testStartingPlayer() throws Exception {
        BoardSize boardSize = new BoardSize(10);
        Game game = new Game(boardSize);
        Player player = new Player(boardSize, new PlayerNumber(1));

        boolean playerOneStarts = player.equals(game.currentPlayer);

        Assertions.assertThat(playerOneStarts).isTrue();
    }
}
