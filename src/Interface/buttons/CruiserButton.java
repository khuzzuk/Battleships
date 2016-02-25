package Interface.buttons;

import Interface.MainWindow;
import Interface.Orientation;
import game.Ship;
import game.ShipType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
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
        MainWindow.mainWindow.placeShipOnBoard(new Ship(ShipType.CA), pointsOnBoard);
    }

    protected void loadImage() {
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("CA.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
