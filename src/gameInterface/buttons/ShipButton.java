package gameInterface.buttons;

import fleet.Ship;
import fleet.ShipType;
import gameInterface.Listeners.MovingButtonAdapter;
import gameInterface.Orientation;
import gameInterface.ShipPlacementWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by adrabik on 22.02.16.
 */
public abstract class ShipButton extends JButton {
    protected Point position;
    protected Point originalPosition;
    protected Dimension shipSize;
    protected Rectangle rectangle;
    protected Orientation orientation;
    protected BufferedImage image;
    protected Point[] pointsOnBoard;
    protected int numberOfShipFields;

    public ShipButton(Point point, int size) {
        numberOfShipFields = size;
        orientation = Orientation.HORIZONTAL;
        setShipSize();
        setOpaque(true);
        originalPosition = position = point;
        relocate(position);
        loadImage();
    }

    public static ShipButton getShipButton(Ship ship, Point startingPosition) {
        if (ship == null) return null;
        ShipButton button;
        if (ship.shipType() == ShipType.BB) {
            button = new BattleshipButton(startingPosition);
        } else if (ship.shipType() == ShipType.CA)
            button = new CruiserButton(startingPosition);
        else if (ship.shipType() == ShipType.DD)
            button = new DestroyerButton(startingPosition);
        else button = new SubmarineButton(startingPosition);
        button.addMouseListener(new MovingButtonAdapter(button));
        button.addMouseMotionListener(new MovingButtonAdapter(button));
        button.addMouseWheelListener(new MovingButtonAdapter(button));
        return button;
    }

    protected abstract void loadImage();

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    public void relocate(Point point) {
        position = point;
        setBounds(new Rectangle(position, shipSize));
        setPreferredSize(shipSize);
        rectangle = new Rectangle(0, 0, shipSize.width - 1, shipSize.height - 1);
    }

    public void relocate(MouseEvent e) {
        Point onScreen = e.getLocationOnScreen();
        Point relativePoint = e.getPoint();
        int windowX = ShipPlacementWindow.shipPlacementWindow.getX();
        int windowY = ShipPlacementWindow.shipPlacementWindow.getY();
        Point location = new Point(onScreen.x - shipSize.width / 2 - windowX - 10, onScreen.y - shipSize.height / 2 - 30 - windowY);
        relocate(location);
    }

    public void changeOrientation() {
        if (orientation == Orientation.HORIZONTAL) {
            orientation = Orientation.VERTICAL;
            position.x += shipSize.width / 2 - shipSize.width / 2 / (shipSize.width / VisibleItem.itemSize);
            position.y -= shipSize.width / 2 - shipSize.width / 2 / (shipSize.width / VisibleItem.itemSize);
        } else {
            orientation = Orientation.HORIZONTAL;
            position.x -= shipSize.height / 2 - shipSize.height / 2 / (shipSize.height / VisibleItem.itemSize);
            position.y += shipSize.height / 2 - shipSize.height / 2 / (shipSize.height / VisibleItem.itemSize);
        }
        int x = shipSize.width;
        shipSize.width = shipSize.height;
        shipSize.height = x;
        relocate(position);
    }

    public ShipButton returnToOriginalPosition() {
        relocate(originalPosition);
        return this;
    }

    public void placeOnBoard(Point point) {
        int numberOfShipFields = (int) Math.max((shipSize.getWidth() - 1) / VisibleItem.itemSize,
                (shipSize.getHeight() - 1) / VisibleItem.itemSize);
        pointsOnBoard = new Point[numberOfShipFields];
        pointsOnBoard[0] = new Point(point.x / VisibleItem.itemSize, point.y / VisibleItem.itemSize);
        for (int x = 1; x < pointsOnBoard.length; x++) {
            if (orientation == Orientation.HORIZONTAL) {
                pointsOnBoard[x] = new Point(pointsOnBoard[x - 1].x + 1, pointsOnBoard[x - 1].y);
            } else pointsOnBoard[x] = new Point(pointsOnBoard[x - 1].x, pointsOnBoard[x - 1].y + 1);
        }
        relocate(point);
    }

    public void resize(int boardSize) {
        setShipSize();
        originalPosition = new Point((boardSize+1) * VisibleItem.itemSize, VisibleItem.itemSize);
        if (pointsOnBoard != null) {
            relocate(new Point(pointsOnBoard[0].x * VisibleItem.itemSize, pointsOnBoard[0].y * VisibleItem.itemSize));
        }
        else {
            returnToOriginalPosition();
        }
    }

    public Dimension getShipSize() {
        return new Dimension(shipSize.width, shipSize.height);
    }

    public Point getPosition() {
        return position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = setRendering(g);
        g2.setColor(new Color(220, 220, 220));
        if (isEnabled())
            g2.setColor(Color.white);
        g2.fill(rectangle);
        g2.setColor(Color.black);
        if (!isEnabled()) g2.setColor(Color.RED);
        g2.draw(rectangle);
        if (orientation == Orientation.VERTICAL) {
            drawRotatedIcon(g2);
        } else {
            g2.drawImage(image, 0, 0, shipSize.width - 1, shipSize.height - 1, null);
        }
    }

    private void drawRotatedIcon(Graphics2D g2) {

        AffineTransform affineTransform = new AffineTransform();
        double rotate = Math.toRadians(90);
        int shipWidth = (shipSize.width + 1) / VisibleItem.itemSize * 2;
        int shipHeight = (shipSize.height + 1) / VisibleItem.itemSize * 2;
        int rotateX = shipSize.height / shipHeight;
        int rotateY = shipSize.width / shipWidth;
        affineTransform.rotate(rotate, rotateX, rotateY);
        g2.setTransform(affineTransform);
        g2.drawImage(image, 0, 1, shipSize.height - 1, shipSize.width - 1, null);
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

    protected void setShipSize() {
        if (orientation == Orientation.HORIZONTAL)
            shipSize = new Dimension(VisibleItem.itemSize * numberOfShipFields + 1, VisibleItem.itemSize + 1);
        else
            shipSize = new Dimension(VisibleItem.itemSize + 1, VisibleItem.itemSize * numberOfShipFields + 1);
    }
}
