import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test1 extends Application {

    public final static int WINDOW_WIDTH = 640;
    public final static int WINDOW_HEIGHT = 440;
    private final int CANVAS_WIDTH = 640;
    private final int CANVAS_HEIGHT = 400;
    private GraphicsContext graphics;

    private Ghost ghost;
    private Sprite ghostSprite;
    private Sprite bg1, bg2;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Flappy ghost");
        stage.setResizable(false);

        Parent root = createContent();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        Image icon = new Image(".\\Images\\ghost.png");
        stage.getIcons().add(icon);
        stage.setOnCloseRequest((event) -> {
            Platform.exit();
        });
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Flappy Ghost");
        stage.setScene(scene);
        stage.show();

        startGame();

    }

    private Group createCanvas() {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        // ImageView bg = setBackground();

        graphics = canvas.getGraphicsContext2D();
        setGhost();
        setBackground();

        root.getChildren().addAll(canvas);
        return root;
    }

    private Parent createContent() {
        Pane root = new Pane();
        Group gameCanvas = createCanvas();

        root.getChildren().addAll(gameCanvas);
        return root;
    }

    private void setBackground() {
        // ImageView bg = new ImageView(new
        // Image(getClass().getResource("./Images/bg.png").toExternalForm()));
        // return bg;

        bg1 = new Sprite();
        bg1.setImage(new Image("./Images/bg.png"));
        bg1.setVelocity(-120, 0);

        bg2 = new Sprite();
        bg2.setImage(new Image("./Images/bg.png"));
        bg2.setVelocity(-120, 0);
        bg2.setPosition(bg1.getWidth(), 0);
        // bg1.render(graphics);

    }

    private void setGhost() {
        ghost = new Ghost();
        ghostSprite = ghost.getGhost();
        ghostSprite.setPosition(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        // ghostSprite.setVelocity(120, 0);
        ghostSprite.render(graphics);
    }

    public void startGame() {

        AnimationTimer gameloop = new AnimationTimer() {
            long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double deltaTime = (now - lastTime) * 1e-9;

                graphics.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

                bg1.render(graphics);
                bg2.render(graphics);
                bg1.update(deltaTime);
                bg2.update(deltaTime);

                if (bg1.getPositionX() <= -CANVAS_WIDTH) {
                    bg1.setPosition(bg2.getPositionX() + bg2.getWidth(), 0);
                } else if (bg2.getPositionX() <= -CANVAS_WIDTH) {
                    bg2.setPosition(bg1.getPositionX() + bg1.getWidth(), 0);
                }

                ghostSprite.update(deltaTime);
                ghostSprite.render(graphics);

                lastTime = now;
            }

        };
        gameloop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
