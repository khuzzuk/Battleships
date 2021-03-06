package gameInterface.buttons;

import fleet.Ship;
import fleet.ShipType;
import gameInterface.ShipPlacementWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by adrabik on 22.02.16.
 */
public class SubmarineButton extends ShipButton {
    public SubmarineButton(Point point) {
        super(point, 1);
        relocate(position);
    }

    @Override
    public void placeOnBoard(Point point) {
        super.placeOnBoard(point);
        ShipPlacementWindow.shipPlacementWindow.placeShipOnBoard(new Ship(ShipType.SS), pointsOnBoard);
    }

    protected void loadImage() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("SS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
