package board.fields;

import board.BoardSize;
import rules.AdjacencyRules;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Field implements Comparable {
    final Point position;
    Sign sign;

    public Field(int x, int y, Sign sign) {
        this.position = new Point(x, y);
        this.sign = sign;

    }

    public Field(int x, int y) {
        position = new Point(x, y);
        sign = Sign.EMPTY;
    }

    public Field(Point p) {
        position = p;
    }

    @Override
    public String toString() {
        return position.x + "x" + position.y + " - " + sign;
    }

    public void markShip() {
        sign = Sign.SHIP;
    }

    public boolean isAdjacent(Field otherField) {
        return (Math.abs(position.x - otherField.position.x) <= 1 &&
                Math.abs(position.y - otherField.position.y) <= 1);
    }

    public List<Point> getAdjacentPositions(BoardSize boardSize) {
        List<Point> list = new ArrayList<>();
        int a = AdjacencyRules.ADJACENCY_DISTANCE;
        Point adjacentPoint;
        for (int i = -a; i <= a; i++) {
            for (int j = -a; j <= a; j++) {
                adjacentPoint = new Point(position.x+i,position.y+j);
                if (isPositionValid(boardSize.size, adjacentPoint))
                    list.add(adjacentPoint);
            }
        }
        list.remove(position);
        return list;
    }
    public boolean isPositionValid(int size, Point p){
        return !(p.y>size-1 || p.y<0 || p.x>size-1 || p.x<0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        return (position.x == field.position.x && position.y == field.position.y);
    }

    @Override
    public int hashCode() {
        return position.x;
    }

    @Override
    public int compareTo(Object o) {
        if (!o.getClass().equals(getClass())) return -1;
        Field f = (Field) o;
        double vector1 = Math.round(Math.sqrt(position.x * position.x + position.y * position.y)) * 100;
        double vector2 = Math.round(Math.sqrt(f.position.x * f.position.x + f.position.y * f.position.y)) * 100;
        return (int) Math.round(vector1 - vector2);
    }

    public boolean hasShip() {
        return sign == Sign.SHIP;
    }

    public boolean markHit() {
        sign = Sign.HIT;
        return true;
    }

    public boolean markMiss() {
        sign = Sign.MISS;
        return true;
    }

    public Sign getStatus() {
        return sign;
    }

    public static int compareToX(Field field, Field otherField) {
        return field.position.x - otherField.position.x;
    }

    public static int compareToY(Field field, Field otherField) {
        return field.position.y - otherField.position.y;
    }

    public int getPositionX() {
        return position.x;
    }

    public int getPositionY() {
        return position.y;
    }
}