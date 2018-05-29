package pathfinder;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import pathfinder.drawers.BoardDrawer;

public class PathfinderCompanion {

    public Button btnNewBoard, btnRandomObstacles, btnRun;
    public Pane pane;

    private BoardDrawer boardDrawer;

    public void initialize(){
        this.boardDrawer = new BoardDrawer(pane);
        this.boardDrawer.drawNewBoard(Constants.SIZE);

        btnNewBoard.setOnAction(e -> boardDrawer.drawNewBoard(Constants.SIZE));
    }
}
