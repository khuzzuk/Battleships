package player;

import board.BoardSize;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

/**
 * Created by Adrian_Drabik on 2/26/2016.
 */
public class PlayerTests {
    @Test
    public void testIdEqualsOtherId() {
        //given
        Player player = new Player(new BoardSize(1),new PlayerNumber(1));
        Player otherPlayer = new Player(new BoardSize(2),new PlayerNumber(1));
        //when
        boolean playersAreEqual = player.equals(otherPlayer);
        //then
        Assertions.assertThat(playersAreEqual).isTrue();
    }
}
