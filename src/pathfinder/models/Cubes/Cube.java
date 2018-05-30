/**
 * Stef De Visscher
 */
package pathfinder.models.Cubes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pathfinder.Constants;
import pathfinder.models.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class Cube extends Rectangle {
    private Coordinate coordinate;
    private Set<Cube> neighbours;
    private boolean obstacle = false;

    public Cube(double x, double y, double width, double height) {
        super(x, y, width, height);
        init();
    }

    private void init(){
        // Color the node
        setStroke(Color.BLACK);
        setFill(Constants.NEUTRAL);

        this.neighbours = new HashSet<>();
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Set<Cube> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Cube> neighbours) {
        this.neighbours = neighbours;
    }

    public void addNeighbour(Cube neighbour) {
        this.neighbours.add(neighbour);
    }

    public void deleteNeighbour(Cube neighbour) { this.neighbours.remove(neighbour); }

    public void deleteNeighbourByCoordinate(Coordinate coordinate) {
        Cube toDelete = null;
        // Loop through all neighbours and find the one with this coordinate
        for (Cube neighbour : neighbours) {
            if (neighbour.getCoordinate().equals(coordinate)) toDelete = neighbour;
        }

        // Remove this neighbour if found
        if(toDelete != null) {
            neighbours.remove(toDelete);
        }
    }

    public boolean isObstacle() {
        return obstacle;
    }

    // Set the node as an OBSTACLE and colors it likewise
    void setObstacle(boolean obstacle) {
        if(obstacle && ! this.obstacle) {
            // Color the node like an OBSTACLE
            setFill(Constants.OBSTACLE);
        } else if(! obstacle && this.obstacle) {
            // This set the node as a normal, empty node
            setFill(Color.WHITE);
        }

        this.obstacle = obstacle;
    }
}
