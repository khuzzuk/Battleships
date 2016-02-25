package Interface.buttons;

import Interface.MainWindow;
import game.Ship;
import game.ShipType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
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
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("SS.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
