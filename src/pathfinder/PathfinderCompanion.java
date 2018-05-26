package pathfinder;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import pathfinder.drawers.BoardDrawer;

public class PathfinderCompanion {

    public Button btnNewBoard, btnRandomObstacles, btnRun;
    public Canvas canvas;

    private BoardDrawer boardDrawer;

    public void initialize(){
        this.boardDrawer = new BoardDrawer(canvas);
        this.boardDrawer.drawNewBoard(20);

        btnNewBoard.setOnAction(e -> boardDrawer.drawNewBoard(20));
    }
}
