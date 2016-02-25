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
        Point[] points = new Point[4];
        points[0] = new Point(point.x/PlaceableItem.ITEM_SIZE, point.y/PlaceableItem.ITEM_SIZE);
        for (int x = 1; x < points.length; x++) {
            if (orientation==Orientation.HORIZONTAL){
                points[x] = new Point(points[x-1].x+1, points[x-1].y);
            }
            else points[x] = new Point(points[x-1].x, points[x-1].y+1);
        }
        MainWindow.mainWindow.placeShipOnBoard(new Ship(ShipType.BB), points);
    }

    protected void loadImage() {
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("CA.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
