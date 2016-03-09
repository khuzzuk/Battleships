package gameInterface.buttons;

import fleet.Ship;
import fleet.ShipType;
import gameInterface.ShipPlacementWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BattleshipButton extends ShipButton {
    public BattleshipButton(Point point) {
        super(point, 4);
        relocate(position);
    }


    @Override
    public void placeOnBoard(Point point) {
        super.placeOnBoard(point);
        ShipPlacementWindow.shipPlacementWindow.placeShipOnBoard(new Ship(ShipType.BB), pointsOnBoard);
    }

    @Override
    protected void loadImage() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("BB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resize(int boardSize) {
        setShipSize();
        super.resize(boardSize);
    }
}
