/**
 * Stef De Visscher
 */
package pathfinder.models.Cubes;

import pathfinder.Constants;

public class Start extends Cube {

    public Start(double x, double y, double width, double height) {
        super(x, y, width, height);
        initializeStart();
    }

    private void initializeStart() {
        setFill(Constants.START);
    }
}
