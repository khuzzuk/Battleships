package Interface.buttons;

import Interface.MainWindow;
import Interface.Orientation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by adrabik on 22.02.16.
 */
public class ShipButton extends JButton implements PlaceableItem {
    protected Point position = new Point(220,10);
    protected Dimension shipSize = new Dimension(150,100);
    protected Rectangle rectangle;
    protected Orientation orientation;

    public ShipButton(Point point) {
        orientation = Orientation.HORIZONTAL;
        setOpaque(true);
        position=point;
        relocate(position);
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    public void relocate(Point point) {
        position=point;
        setBounds(new Rectangle(position,shipSize));
        setPreferredSize(shipSize);
        rectangle = new Rectangle(0,0,shipSize.width-1,shipSize.height-1);
    }
    public void relocate(MouseEvent e){
        Point onScreen = e.getLocationOnScreen();
        Point relativePoint = e.getPoint();
        int windowX = MainWindow.mainWindow.getX();
        int windowY = MainWindow.mainWindow.getY();
        Point location = new Point(onScreen.x-shipSize.width/2-windowX,onScreen.y-shipSize.height/2-windowY);
        relocate(location);
    }
    public void changeOrientation(){
        if (orientation==Orientation.HORIZONTAL) {
            orientation=Orientation.VERTICAL;
            position.x+=shipSize.width/3;
            position.y-=shipSize.height;
        }
        else {
            orientation=Orientation.HORIZONTAL;
            position.x-=shipSize.width;
            position.y+=shipSize.height/4;
        }
        int x = shipSize.width;
        shipSize.width=shipSize.height;
        shipSize.height=x;
        relocate(position);
    }

    public void placeOnBoard(Point point){
        relocate(point);
    }

    public Dimension getShipSize() {
        return new Dimension(shipSize.width,shipSize.width);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = setRendering(g);
        if (getMousePosition()!=null) g2.setColor(Color.BLUE);
        else g2.setColor(Color.black);
        g2.fill(rectangle);
    }

    protected Graphics2D setRendering(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2;
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
