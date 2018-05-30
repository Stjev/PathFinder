package pathfinder;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import pathfinder.algorithms.AStarAlgorithm;
import pathfinder.drawers.BoardDrawer;

public class PathfinderCompanion {

    public Button btnNewBoard, btnRandomObstacles, btnRun;
    public Pane pane;

    private BoardDrawer boardDrawer;

    public void initialize(){
        boardDrawer = new BoardDrawer(pane);
        boardDrawer.drawNewBoard();

        new AStarAlgorithm().run(boardDrawer.getNodes(), boardDrawer.getStart(), boardDrawer.getFinish());

        btnNewBoard.setOnAction(e -> boardDrawer.drawNewBoard());
    }
}
