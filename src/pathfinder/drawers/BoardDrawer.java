/**
 * Stef De Visscher
 */
package pathfinder.drawers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pathfinder.models.Coordinate;

import java.util.HashMap;

public class BoardDrawer {

    private Canvas canvas;
    private GraphicsContext gc;
    private HashMap<Coordinate, Chunk> chunks;
    private Integer sideCount;
    private double canvasSize;

    /**
     * TODO: Start and finish
     */

    /**
     * Constructor
     * @param canvas, the canvas to draw on.
     */
    public BoardDrawer(Canvas canvas){
        this.canvas = canvas;
        // Since the canvas is a square, the height and width are the same size
        this.canvasSize = canvas.getHeight();
        gc = this.canvas.getGraphicsContext2D();

        initializeGraphicsContext();
        initializeDrawingEvent();
    }

    private void initializeDrawingEvent() {
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::mouseDraw);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::mouseDraw);
    }

    /**
     * Is called when a mouse is dragged or clicked
     */
    private void mouseDraw(MouseEvent event) {
        double x = event.getX(), y = event.getY();
        if(this.sideCount != null && x < canvasSize && y < canvasSize && x > 0 && y > 0) {
            // round off the values
            x = x - (x % (canvasSize / sideCount));
            y = y - (y % (canvasSize / sideCount));

            // Generate a coordinate for this posititon
            Coordinate coord = new Coordinate(x, y);

            Chunk chunk = chunks.get(coord);

            // Check which button is pressed
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if(! chunk.isObstacle()) {
                    // Set the color to the colors of an obstacle
                    gc.setFill(Color.BLUE);
                    // Draw this chunk
                    chunk.setAsObstacle(true);
                    chunk.draw();
                    // Set the color back to the colors of a path chunk
                    gc.setFill(Color.GREEN);
                }
            } else if(event.getButton().equals(MouseButton.SECONDARY)) {
                if(chunk.isObstacle()) {
                    // This will delete the chunk
                    chunk.delete();
                }
            }
        }
    }

    /**
     * Sets the values for the graphics context
     */
    private void initializeGraphicsContext() {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
    }

    /**
     * Draws the board
     * @param sideCount: The amount of blocks in one row / column.
     */
    public HashMap<Coordinate, Chunk> drawNewBoard(int sideCount) {
        // Clear the canvas first
        gc.clearRect(0, 0, canvasSize, canvasSize);

        this.sideCount = sideCount;

        // Since a canvas is always square, the canvas height is equal to it's width
        double squareSize = canvasSize / sideCount;

        chunks = new HashMap<>();

        // These loops generate the pathChunks
        int rowCounter = 0, colCounter = 0;
        for(double row = 0; row <= canvasSize; row += squareSize) {
            for(double col = 0; col <= canvasSize; col += squareSize) {
                // Generate a coordinate for this posititon
                Coordinate coord = new Coordinate(col, row, rowCounter, colCounter);
                // Generate a path chunk for this position
                Chunk chunk = new Chunk(coord, gc, squareSize);

                chunks.put(coord, chunk);
                colCounter += 0;
            }
            rowCounter += 1;
        }

        return chunks;
    }

    /**
     * Get the map of the chunks
     */
    public HashMap<Coordinate, Chunk> getChunks() {
        return chunks;
    }
}
