/**
 * Stef De Visscher
 */
package pathfinder.models;

import pathfinder.Constants;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Coordinate {
    private final int row, col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * Returns a set of neighbours
     * @return
     */
    public Set<Coordinate> getNeighbours() {
        Set<Coordinate> neighbours = new HashSet<>();

        if(row - 1 >= 0) neighbours.add(new Coordinate(row - 1, col));
        if(row + 1 < Constants.SIZE) neighbours.add(new Coordinate(row + 1, col));
        if(col - 1 >= 0) neighbours.add(new Coordinate(row, col - 1));
        if(col + 1 < Constants.SIZE) neighbours.add(new Coordinate(row, col + 1));

        return neighbours;
    }

    /**
     * Override the hashcode so two coordinates are compared by their x and y values.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Coordinate && this.hashCode() == o.hashCode();
    }

    @Override
    public String toString() {
        return "row: " + row + " - col: " + col;
    }
}
