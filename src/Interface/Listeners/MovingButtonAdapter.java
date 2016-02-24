package Interface.Listeners;

import Interface.MainWindow;
import Interface.buttons.PlaceableItem;
import Interface.buttons.ShipButton;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Created by adrabik on 23.02.16.
 */
public class MovingButtonAdapter extends MouseInputAdapter {
    private ShipButton button;

    public MovingButtonAdapter(ShipButton button) {
        this.button = button;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button.relocate(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int buttonX = button.getBounds().x;
        int buttonY = button.getBounds().y;
        Dimension shipSize = button.getShipSize();
        int buttonWidth = shipSize.width;
        int buttonHeight = shipSize.height;
        Point shipLocation = new Point(
                buttonX+buttonWidth/2 -PlaceableItem.ITEM_SIZE,
                buttonY- buttonHeight/2 +PlaceableItem.ITEM_SIZE);
        Point fieldPoint = MainWindow.mainWindow.getNearestFieldLocation(shipLocation, shipSize);
        if (fieldPoint==null){
            button.returnToOriginalPosition();
        } else{
            //fieldPoint.x+= PlaceableItem.ITEM_SIZE;
            //fieldPoint.y+= PlaceableItem.ITEM_SIZE;
            button.placeOnBoard(fieldPoint);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        button.relocate(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        button.changeOrientation();
    }
}
