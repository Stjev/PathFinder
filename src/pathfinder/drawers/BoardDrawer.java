/**
 * Stef De Visscher
 */
package pathfinder.drawers;

import javafx.scene.layout.Pane;
import pathfinder.Constants;
import pathfinder.models.Coordinate;
import pathfinder.models.Cubes.Finish;
import pathfinder.models.Cubes.Node;
import pathfinder.models.Cubes.Start;

import java.util.HashMap;

public class BoardDrawer {

    private Pane pane;
    private HashMap<Coordinate, Node> nodes;
    private double paneSize;
    private double squareSize;
    private Start start;
    private Finish finish;

    /**
     * TODO: Start and FINISH
     */

    /**
     * Constructor
     */
    public BoardDrawer(Pane pane){
        this.pane = pane;
        // Since the canvas is a square, the height and width are the same SIZE
        this.paneSize = pane.getPrefHeight();
    }

    /**
     * Draw the start and the finish
     */
    private void drawStartAndFinish() {
        start = new Start(0, 0, squareSize, squareSize);
        finish = new Finish(paneSize - squareSize, paneSize - squareSize, squareSize, squareSize);

        pane.getChildren().addAll(start, finish);
    }

    public Start getStart() {
        return start;
    }

    public Finish getFinish() {
        return finish;
    }

    /**
     * Draws the board
     */
    public HashMap<Coordinate, Node> drawNewBoard() {
        // Clear the pane first
        pane.getChildren().clear();
        // Since a canvas is always square, the canvas height is equal to it's width
        this.squareSize = (paneSize / Constants.SIZE);

        nodes = new HashMap<>();

        // These loops generate the pathChunks
        int rowCounter = 0;
        for(double row = 0; row <= paneSize; row += squareSize) {
            int colCounter = 0;
            for(double col = 0; col <= paneSize; col += squareSize) {
                // Generate a coordinate for this posititon
                Coordinate coord = new Coordinate(rowCounter, colCounter);
                // Generate a node for this position and draw it.
                Node node = new Node(col, row, squareSize, squareSize);
                node.setCoordinate(coord);

                for(Coordinate neighbour : coord.getNeighbours()) {
                    if(nodes.containsKey(neighbour)) {
                        node.addNeighbour(nodes.get(neighbour));
                        nodes.get(neighbour).addNeighbour(node);
                    }
                }

                // Put the node in the map
                nodes.put(coord, node);
                // Add the node to the pane
                pane.getChildren().add(node);
                colCounter += 1;
            }
            rowCounter += 1;
        }

        drawStartAndFinish();
        return nodes;
    }

    /**
     * Get the map of the chunks
     */
    public HashMap<Coordinate, Node> getNodes() {
        return nodes;
    }
}
