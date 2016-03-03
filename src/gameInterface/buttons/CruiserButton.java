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
public class CruiserButton extends ShipButton {
    public CruiserButton(Point point) {
        super(point);
        shipSize = new Dimension(PlaceableItem.ITEM_SIZE *3+1, PlaceableItem.ITEM_SIZE+1);
        relocate(position);
    }

    @Override
    public void placeOnBoard(Point point) {
        super.placeOnBoard(point);
        ShipPlacementWindow.shipPlacementWindow.placeShipOnBoard(new Ship(ShipType.CA), pointsOnBoard);
    }

    protected void loadImage() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("CA.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
