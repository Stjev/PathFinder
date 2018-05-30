/**
 * Stef De Visscher
 */
package pathfinder.models.Cubes;

import pathfinder.Constants;

public class Finish extends Node {
    public Finish(double x, double y, double width, double height) {
        super(x, y, width, height);
        initializeStart();
    }

    private void initializeStart() {
        setFill(Constants.FINISH);
    }
}