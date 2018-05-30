/**
 * Stef De Visscher
 */
package pathfinder.drawers;

import javafx.scene.layout.Pane;
import pathfinder.Constants;
import pathfinder.models.Coordinate;
import pathfinder.models.Cubes.Cube;
import pathfinder.models.Cubes.Finish;
import pathfinder.models.Cubes.Node;
import pathfinder.models.Cubes.Start;

import java.util.HashMap;
import java.util.HashSet;

public class BoardDrawer {

    private Pane pane;
    private HashMap<Coordinate, Node> nodes;
    private double paneSize;
    private double squareSize;
    private Start start;
    private Finish finish;

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
        // Generate a start and set it's coordinate
        start = new Start(0, 0, squareSize, squareSize);
        start.setCoordinate(new Coordinate(0, 0));
        start.getCoordinate().getNeighbours().forEach(c -> replaceNeighbour(start, nodes.get(c)));

        // Generate a finish and set it's coordinate
        finish = new Finish(paneSize - squareSize, paneSize - squareSize, squareSize, squareSize);
        finish.setCoordinate(new Coordinate(Constants.SIZE - 1, Constants.SIZE - 1));
        finish.getCoordinate().getNeighbours().forEach(c -> replaceNeighbour(finish, nodes.get(c)));

        pane.getChildren().removeAll(
                nodes.get(start.getCoordinate()),
                nodes.get(finish.getCoordinate())
        );

        // Display both of them
        pane.getChildren().addAll(start, finish);
    }

    /**
     * Method to replace the neighbours of a node
     */
    private void replaceNeighbour(Cube newNeighbour, Cube replaceNeighboursCube) {
        newNeighbour.addNeighbour(replaceNeighboursCube);
        replaceNeighboursCube.deleteNeighbourByCoordinate(newNeighbour.getCoordinate());
        replaceNeighboursCube.addNeighbour(newNeighbour);
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
    public void drawNewBoard() {
        // Clear the pane first
        pane.getChildren().clear();
        // Since a canvas is always square, the canvas height is equal to it's width
        this.squareSize = (paneSize / Constants.SIZE);

        nodes = new HashMap<>();

        // These loops generate the pathChunks
        for(int row = 0; row < Constants.SIZE; row += 1) {
            for(int col = 0; col < Constants.SIZE; col += 1) {
                // Generate a coordinate for this posititon
                Coordinate coord = new Coordinate(row, col);
                // Generate a node for this position and draw it.
                Node node = new Node(col * squareSize, row * squareSize, squareSize, squareSize);
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
            }
        }

        drawStartAndFinish();
    }

    /**
     * Get the map of the chunks
     */
    public HashSet<Node> getNodes() {
        return new HashSet<>(nodes.values());
    }
}
