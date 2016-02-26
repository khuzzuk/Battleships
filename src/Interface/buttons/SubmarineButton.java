package Interface.buttons;

import Interface.MainWindow;
import game.fleet.Ship;
import game.fleet.ShipType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by adrabik on 22.02.16.
 */
public class SubmarineButton extends ShipButton {
    public SubmarineButton(Point point) {
        super(point);
        loadImage();
        shipSize = new Dimension(PlaceableItem.ITEM_SIZE+1, PlaceableItem.ITEM_SIZE+1);
        relocate(position);
    }

    @Override
    public void placeOnBoard(Point point) {
        super.placeOnBoard(point);
        MainWindow.mainWindow.placeShipOnBoard(new Ship(ShipType.SS), pointsOnBoard);
    }

    protected void loadImage() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("SS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
