package Interface.buttons;

import Interface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by adrabik on 22.02.16.
 */
public class ShipButton extends JButton {
    protected static final int rectangleSize = 20;
    protected Point position = new Point(220,10);
    protected Dimension shipSize = new Dimension(150,100);
    protected Rectangle rectangle;

    public ShipButton(Point point) {
        setOpaque(true);
        position=point;
        relocate(position);
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    public void relocate(Point point) {
        int windowX = MainWindow.mainWindow.getX();
        int windowY = MainWindow.mainWindow.getY();
        position=new Point(point.x-windowX+shipSize.width/2, point.y-windowY+shipSize.height/2);
        setBounds(new Rectangle(position,shipSize));
        //setBounds(0,0,300,300);
        setPreferredSize(shipSize);
        rectangle = new Rectangle(0,0,shipSize.width-1,shipSize.height-1);
    }
    public void relocate(MouseEvent e){
        Point onScreen = e.getLocationOnScreen();
        Point relativePoint = e.getPoint();
        Point location = new Point(onScreen.x-shipSize.width,onScreen.y-shipSize.height);
        relocate(location);
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
