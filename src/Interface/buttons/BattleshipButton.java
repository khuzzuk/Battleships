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
import java.util.ArrayList;

/**
 * Created by adrabik on 22.02.16.
 */
public class BattleshipButton extends ShipButton {
    public BattleshipButton(Point point) {
        super(point);
        shipSize = new Dimension(PlaceableItem.ITEM_SIZE *4+1, PlaceableItem.ITEM_SIZE+1);
        relocate(position);
    }

    @Override
    public void placeOnBoard(Point point) {
        super.placeOnBoard(point);
        MainWindow.mainWindow.placeShipOnBoard(new Ship(ShipType.BB), pointsOnBoard);
    }

    @Override
    protected void loadImage() {
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("BB.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
