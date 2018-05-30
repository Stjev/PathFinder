package pathfinder.interfaces;

import pathfinder.models.Coordinate;
import pathfinder.models.Cubes.Finish;
import pathfinder.models.Cubes.Node;
import pathfinder.models.Cubes.Start;

import java.util.HashMap;

public interface Algoritm {
    void run(HashMap<Coordinate, Node> nodes, Start start, Finish finish);
}
