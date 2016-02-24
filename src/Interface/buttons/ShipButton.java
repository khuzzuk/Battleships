package Interface.buttons;

import Interface.MainWindow;
import Interface.Orientation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by adrabik on 22.02.16.
 */
public abstract class ShipButton extends JButton implements PlaceableItem {
    protected Point position;
    protected Point originalPosition;
    protected Dimension shipSize = new Dimension(150,100);
    protected Rectangle rectangle;
    protected Orientation orientation;
    protected BufferedImage image;

    public ShipButton(Point point) {
        orientation = Orientation.HORIZONTAL;
        setOpaque(true);
        originalPosition=position=point;
        relocate(position);
        loadImage();
    }

    protected abstract void loadImage();

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
        Point location = new Point(onScreen.x-shipSize.width/2-windowX-10,onScreen.y-shipSize.height/2-30-windowY);
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
    public void returnToOriginalPosition(){
        relocate(originalPosition);
    }

    public void placeOnBoard(Point point){
        relocate(point);
    }

    public Dimension getShipSize() {
        return new Dimension(shipSize.width,shipSize.height);
    }

    public Point getPosition() {
        return position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = setRendering(g);
        g2.setColor(Color.white);
        g2.fill(rectangle);
        g2.setColor(Color.black);
        g2.draw(rectangle);
        if (orientation==Orientation.VERTICAL) {
            drawRotatedIcon(g2);
        } else{
            g2.drawImage(image,0,0,shipSize.width-1,shipSize.height-1,null);
        }
    }

    private void drawRotatedIcon(Graphics2D g2) {

        AffineTransform affineTransform = new AffineTransform();
        double rotate = Math.toRadians(90);
        int shipWidth = (shipSize.width+1)/PlaceableItem.ITEM_SIZE*2;
        int shipHeight = (shipSize.height+1)/PlaceableItem.ITEM_SIZE*2;
        int rotateX = shipSize.height/shipHeight;
        int rotateY = shipSize.width/shipWidth;
        affineTransform.rotate(rotate,rotateX,rotateY);
        g2.setTransform(affineTransform);
        g2.drawImage(image,0,1,shipSize.height-1,shipSize.width-1,null);
        repaint();
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
