/**
 * Stef De Visscher
 */
package pathfinder.algorithms;

import javafx.scene.control.Alert;
import pathfinder.Constants;
import pathfinder.interfaces.Algorithm;
import pathfinder.models.Cubes.Cube;
import pathfinder.models.Cubes.Finish;
import pathfinder.models.Cubes.Node;
import pathfinder.models.Cubes.Start;

import java.util.*;

public class AStarAlgorithm implements Algorithm {

    private Set<Cube> lastPath, evaluated;
    private Map<Cube, Cube> cameFrom;
    private Map<Cube, Integer> costFromStart, costToGoal;
    private PriorityQueue<Cube> toEvaluate;
    private Finish finish;

    @Override
    public void run(HashSet<Node> nodes, Start start, Finish finish, boolean instant) {
        this.finish = finish;

        lastPath = new HashSet<>();

        nodes.stream().filter(n -> ! n.isObstacle()).forEach(n -> n.setFill(Constants.NEUTRAL));

        // The evaluated nodes
        evaluated = new HashSet<>();

        // Map with a cube as key and as value the cube it came from
        cameFrom = new HashMap<>();

        // A map of the cost to get to this node from the start
        costFromStart = new HashMap<>();

        // A map of the cost to get to the finish
        costToGoal = new HashMap<>();

        // Put all nodes in the map with the
        nodes.forEach(n -> {
                    costToGoal.put(n, Integer.MAX_VALUE);
                    costFromStart.put(n, Integer.MAX_VALUE);
                });
        costToGoal.put(finish, 0);
        costFromStart.put(finish, Integer.MAX_VALUE);

        // The cost to get from the start to the start is 0
        costFromStart.put(start, 0);

        // The cost to get to
        costToGoal.put(start, estimateCostToGoal(start, finish));

        // The nodes to evaluate, sorted by the lowest cost to goal
        toEvaluate = new PriorityQueue<>(Comparator.comparingInt(costToGoal::get));
        toEvaluate.add(start);

        // Does the algorithm need to be instant
        if(instant) {
            // TODO: Werkt alleen als de code in de loop staat
            while (! toEvaluate.isEmpty()) {
                doStep();
            }
        } else {
            //TODO: Crashed met NullPointer
            // Not instant, does a step every 10ms.
            Timer loop = new Timer();
            loop.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            if(! toEvaluate.isEmpty()) {
                                if(doStep()) {
                                    loop.cancel();
                                    loop.purge();
                                }
                            }
                        }
                    },
                    0,
                    15
            );
        }

        //TODO: Broken
        if(toEvaluate.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No path found to the finish node.");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    /**
     * Does a step in the algoritm,
     * returns true if this was the final step and the finish node is reached.
     */
    private boolean doStep() {
        Cube current = toEvaluate.poll();

        if(current.equals(finish)) {
            drawPath(cameFrom, finish, finish);
            return true;
        }

        // Set this cube as evaluated
        toEvaluate.remove(current);
        evaluated.add(current);

        for (Cube neighbour : current.getNeighbours()) {
            // This neighbour has already been evaluated, skip this one
            if(evaluated.contains(neighbour) || neighbour.isObstacle()) continue;

            // This is an undiscovered neighbour
            if(! toEvaluate.contains(neighbour)) {
                toEvaluate.add(neighbour);
            }

            // The cost from the neighbour to the start is this cost + 1
            Integer neighbourCostFromStart = costFromStart.get(current) + 1;
            if(neighbourCostFromStart > costFromStart.get(neighbour)) continue;

            // The neighbour node came from the current node
            cameFrom.put(neighbour, current);
            costFromStart.put(neighbour, neighbourCostFromStart);
            costToGoal.put(neighbour, neighbourCostFromStart + estimateCostToGoal(neighbour, finish));

            drawPath(cameFrom, neighbour, finish);
        }

        return false;
    }

    /**
     * Will look for the estimated distance to the goal.
     * @param node: The cube where the path is currently at.
     * @param finish: The goal where the cube needs to go
     * @return an integer with the estimated distance
     */
    private Integer estimateCostToGoal(Cube node, Finish finish) {
        return Math.abs(finish.getCoordinate().getRow() - node.getCoordinate().getRow()) +
                Math.abs(finish.getCoordinate().getCol() - node.getCoordinate().getCol());
    }

    private void drawPath(Map<Cube, Cube> path, Cube current, Finish finish) {
        for (Cube cube : lastPath) {
            cube.setFill(Constants.NEUTRAL);
        }

        lastPath.clear();

        // This makes it so the finish isn't colored
        if(current.equals(finish)) current = path.get(finish);

        // While there is a previous cube before the current cube
        while(path.containsKey(current)) {
            // Color the cube
            current.setFill(Constants.PATH);
            // Add the cube to the path
            lastPath.add(current);
            // Set a new current cube
            current = path.get(current);
        }
    }
}
