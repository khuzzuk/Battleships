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
        Point point = MainWindow.mainWindow.getNearestFieldLocation(e.getLocationOnScreen());
        if (point==null){
            button.relocate(e);
        } else{
            point.x-=button.getShipSize().width;
            point.y-=button.getShipSize().height- PlaceableItem.ITEM_SIZE;
            button.placeOnBoard(point);
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
