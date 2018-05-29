/**
 * Stef De Visscher
 */
package pathfinder.drawers;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pathfinder.models.Coordinate;
import pathfinder.models.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BoardDrawer {

    private Pane pane;
    private HashMap<Coordinate, Node> nodes;
    private double paneSize;
    private double squareSize;

    /**
     * TODO: Start and finish
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
     * Draws the board
     * @param sideCount: The amount of blocks in one row / column.
     */
    public HashMap<Coordinate, Node> drawNewBoard(int sideCount) {
        // Clear the pane first
        pane.getChildren().clear();
        // Since a canvas is always square, the canvas height is equal to it's width
        this.squareSize = (paneSize / sideCount);

        nodes = new HashMap<>();

        // These loops generate the pathChunks
        int rowCounter = 0;
        for(double row = 0; row <= paneSize; row += squareSize) {
            int colCounter = 0;
            for(double col = 0; col <= paneSize; col += squareSize) {
                // Generate a coordinate for this posititon
                Coordinate coord = new Coordinate(rowCounter, colCounter);
                // Generate a node for this position and draw it.
                Node node = new Node(row, col, squareSize, squareSize);
                node.setCoordinate(coord);
                // Put the node in the map
                nodes.put(coord, node);

                /**
                 * TODO: Adds nodes, that are k*32 rows after this node for some reason.
                 */
                for(Coordinate neighbour : coord.getNeighbours()) {
                    if(nodes.keySet().contains(neighbour)) {
                        node.addNeighbour(nodes.get(neighbour));
                        nodes.get(neighbour).addNeighbour(node);
                    }
                }
                // Add the node to the pane
                pane.getChildren().add(node);

                colCounter += 1;
            }
            rowCounter += 1;
        }

        return nodes;
    }

    /**
     * Get the map of the chunks
     */
    public HashMap<Coordinate, Node> getChunks() {
        return nodes;
    }
}
