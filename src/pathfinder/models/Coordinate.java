/**
 * Stef De Visscher
 */
package pathfinder.models;

import java.util.Objects;

public class Coordinate {
    private final double x, y;
    private final int rowNumber, colNumber;

    public Coordinate(double x, double y) {
        this(x, y, 0, 0);
    }

    public Coordinate(double x, double y, int rowNumber, int colNumber) {
        this.x = x;
        this.y = y;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    /**
     * Override the hashcode so two coordinates are compared by their x and y values.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Coordinate && this.hashCode() == o.hashCode();
    }
}
