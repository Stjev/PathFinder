/**
 * Stef De Visscher
 */
package pathfinder.models;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;

public class Node extends Rectangle implements EventHandler<MouseEvent>{

    private boolean obstacle = false;
    private Coordinate coordinate;
    private Set<Node> neighbours;

    public Node(double width, double height) {
        super(width, height);
        init();
    }

    public Node(double width, double height, Paint fill) {
        super(width, height, fill);
        init();
    }

    public Node(double x, double y, double width, double height) {
        super(x, y, width, height);
        init();
    }

    private void init(){
        // Color the node
        setStroke(Color.BLACK);
        setFill(Color.WHITE);

        this.neighbours = new HashSet<>();
        addHandlers();
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    // Set the node as an obstacle and colors it likewise
    private void setObstacle(boolean obstacle) {
        if(obstacle && ! this.obstacle) {
            // Color the node like an obstacle
            setFill(Color.BLUE);
        } else if(! obstacle && this.obstacle) {
            // This set the node as a normal, empty node
            setFill(Color.WHITE);
        }

        this.obstacle = obstacle;
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

    /**
     *
     * EventHandler
     *
     */

    /**
     * Sets the handlers for the mouse events
     */
    private void addHandlers() {
        this.setOnMouseClicked(this);
        this.setOnMouseDragOver(this);
        // Makes other nodes able to detect the drag over event
        this.setOnDragDetected(e -> this.startFullDrag());
    }

    /**
     * Sets the node as an obstacle
     */
    @Override
    public void handle(MouseEvent event) {
        // Check which button is pressed
        if(event.getButton().equals(MouseButton.PRIMARY)) {
            setObstacle(true);
        } else if(event.getButton().equals(MouseButton.SECONDARY)) {
            setObstacle(false);
        }

        System.out.println("mine: " + coordinate);

        for(Node nb : neighbours) {
            nb.setFill(Color.RED);
            System.out.println("theirs: " + nb.getCoordinate());
        }

        System.out.println("------------------------");
    }
}
