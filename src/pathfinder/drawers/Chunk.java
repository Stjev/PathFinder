/**
 * Stef De Visscher
 */
package pathfinder.drawers;

import javafx.scene.canvas.GraphicsContext;
import pathfinder.models.Coordinate;

import java.util.ArrayList;

public class Chunk {

    private Coordinate coord;
    private GraphicsContext gc;
    private ArrayList<Chunk> neighbours;
    private double size;
    private boolean obstacle = false, drawn = false;

    public Chunk(Coordinate coord, GraphicsContext gc, double size) {
        this.coord = coord;
        this.gc = gc;
        this.size = size;
        gc.strokeRect(coord.getX(), coord.getY(), size, size);
    }

    public ArrayList<Chunk> getNeighbours() {
        return neighbours;
    }

    public void setAsObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public boolean isObstacle() {
        return this.obstacle;
    }

    public double getSize() {
        return size;
    }

    public void draw() {
        gc.fillRect(coord.getX(), coord.getY(), size, size);
        gc.strokeRect(coord.getX(), coord.getY(), size, size);
    }

    public void delete() {
        gc.clearRect(coord.getX(), coord.getY(), size, size);
        gc.strokeRect(coord.getX(), coord.getY(), size, size);
        setAsObstacle(false);
    }
}
