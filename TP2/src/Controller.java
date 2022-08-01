import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private AnchorPane pane;

    @FXML
    private Canvas canvas;

    @FXML
    private Circle ghost;

    private int jumpHeight = 300;
    private double speed = 120;
    private int gravity = 500;

}
