package game;

import java.awt.*;

/**
 * Created by adrabik on 23.02.16.
 */
public final class Field implements Comparable {
    final Point position;
    Sign sign;

    public Field(int x, int y, Sign sign) {
        this.position = new Point(x, y);
        this.sign = sign;

    }

    public Field(int x, int y) {
        position = new Point(x,y);
        sign=Sign.EMPTY;
    }

    @Override
    public String toString() {
        return sign.toString();
    }

    public void markShip() {
        sign = Sign.SHIP;
    }
    public boolean isAdjacent(Field otherField){
        if (Math.abs(position.x-otherField.position.x)<=1 &&
                Math.abs(position.y-otherField.position.y)<=1) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        return (position.x==field.position.x && position.y==field.position.y);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }

    //@Override
    public int compareTo(Object o) {
        if (!o.getClass().equals(getClass())) return -1;
        Field f = (Field) o;
        double vector1 = Math.round(Math.sqrt(position.x*position.x+position.y*position.y))*100;
        double vector2 = Math.round(Math.sqrt(f.position.x*f.position.x+f.position.y*f.position.y))*100;
        return (int) Math.round(vector1-vector2);
    }

    public boolean hasShip(){
        return sign==Sign.SHIP;
    }
    public boolean markHit(){
        sign=Sign.HIT;
        return true;
    }
    public boolean markMiss(){
        sign=Sign.MISS;
        return true;
    }

    public boolean shoot() {
        if (sign == Sign.EMPTY) {
            sign = Sign.MISS;
            return false;
        } else {
            sign = Sign.HIT;
            return true;
        }
    }

    public Sign getStatus() {
        return sign;
    }
}