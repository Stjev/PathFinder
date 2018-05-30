/**
 * Stef De Visscher
 */
package pathfinder.models.Cubes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import pathfinder.Constants;

public class Node extends Cube implements EventHandler<MouseEvent>{
    private boolean obstacle = false;

    public Node(double x, double y, double width, double height) {
        super(x, y, width, height);
        init();
    }

    private void init(){
        addHandlers();
    }

    public boolean isObstacle() {
        return obstacle;
    }

    // Set the node as an OBSTACLE and colors it likewise
    private void setObstacle(boolean obstacle) {
        if(obstacle && ! this.obstacle) {
            // Color the node like an OBSTACLE
            setFill(Constants.OBSTACLE);
        } else if(! obstacle && this.obstacle) {
            // This set the node as a normal, empty node
            setFill(Color.WHITE);
        }

        this.obstacle = obstacle;
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
     * Sets the node as an OBSTACLE
     */
    @Override
    public void handle(MouseEvent event) {
        // Check which button is pressed
        if(event.getButton().equals(MouseButton.PRIMARY)) {
            setObstacle(true);
        } else if(event.getButton().equals(MouseButton.SECONDARY)) {
            setObstacle(false);
        }
    }
}
