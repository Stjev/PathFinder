/**
 * Stef De Visscher
 */
package pathfinder.models.Cubes;

import javafx.scene.shape.Rectangle;
import pathfinder.models.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class Cube extends Rectangle {
    private Coordinate coordinate;
    private Set<Node> neighbours;

    public Cube(double x, double y, double width, double height) {
        super(x, y, width, height);
        init();
    }

    private void init(){
        // Color the node
        setStroke(javafx.scene.paint.Color.BLACK);
        setFill(javafx.scene.paint.Color.WHITE);

        this.neighbours = new HashSet<>();
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Set<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public void addNeighbour(Node neighbour) {
        this.neighbours.add(neighbour);
    }
}
