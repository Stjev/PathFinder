package pathfinder.interfaces;

import pathfinder.models.Cubes.Finish;
import pathfinder.models.Cubes.Node;
import pathfinder.models.Cubes.Start;

import java.util.HashSet;

public interface Algorithm {
    /**
     * This method will run the algorithm
     * @param nodes: The current map of the nodes with their coordinate
     * @param start: The starting point for the algorithm
     * @param finish: The goal for the algorithm
     * @param instant: Does the algorithm need to be instant or show the process
     */
    void run(HashSet<Node> nodes, Start start, Finish finish, boolean instant) throws InterruptedException;
}
