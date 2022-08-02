package FlappyGhost;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test1 extends Application {

    public final static int WINDOW_WIDTH = 640;
    public final static int WINDOW_HEIGHT = 440;
    private final int CANVAS_WIDTH = 640;
    private final int CANVAS_HEIGHT = 400;

    private Timer timer;

    private GraphicsContext graphics;

    private ArrayList<Sprite> obstacles = new ArrayList<>();
    // private Sprite obstacle;
    private Sprite ghost;
    private Sprite bg1, bg2;

    private int speed = 120;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Flappy ghost");
        stage.setResizable(false);

        Parent root = createContent();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        scene.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.SPACE) {
                ghost.addVelocity(0, -300);
            }
        });

        Image icon = new Image(".\\Images\\ghost.png");
        stage.getIcons().add(icon);
        stage.setOnCloseRequest((event) -> {
            timer.cancel();
            Platform.exit();

        });

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Flappy Ghost");
        stage.setScene(scene);
        stage.show();

        startGame();

    }

    private Parent createContent() {
        Pane root = new Pane();
        Group canvas = createCanvas();

        Platform.runLater(() -> {
            canvas.requestFocus();
        });

        root.setOnMouseClicked((e) -> {
            canvas.requestFocus();
        });

        root.getChildren().addAll(canvas);
        return root;
    }

    private Group createCanvas() {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        // ImageView bg = setBackground();
        // Platform.runLater(() -> {
        // canvas.requestFocus();
        // });

        graphics = canvas.getGraphicsContext2D();

        timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                createNewObstacle();
            }
        };
        timer.scheduleAtFixedRate(task, 3000, 3000);

        setGhost();
        setBackground();

        root.getChildren().addAll(canvas);
        return root;
    }

    private void setBackground() {
        // ImageView bg = new ImageView(new
        // Image(getClass().getResource("./Images/bg.png").toExternalForm()));
        // return bg;

        bg1 = new Sprite();
        bg1.setImage(new Image("./Images/bg.png"));
        bg1.setVelocity(-speed, 0);

        bg2 = new Sprite();
        bg2.setImage(new Image("./Images/bg.png"));
        bg2.setVelocity(-speed, 0);
        bg2.setPosition(bg1.getWidth(), 0);

    }

    private void setGhost() {
        ghost = new Ghost().getGhost();
        ghost.setPosition(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
    }

    // private void setObstacle() {
    // obstacle = new Obstacle().getObstacle(1);
    // }

    private void createNewObstacle() {
        Sprite newObstacle = new Obstacle().getObstacle(1);
        obstacles.add(newObstacle);
    }

    public void startGame() {

        AnimationTimer gameloop = new AnimationTimer() {
            long lastTime = 0;
            double gravity = 500;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double deltaTime = (now - lastTime) * 1e-9;

                graphics.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

                // Background scrolling
                bg1.render(graphics);
                bg2.render(graphics);
                bg1.update(deltaTime);
                bg2.update(deltaTime);

                if (bg1.getPositionX() <= -CANVAS_WIDTH) {
                    bg1.setPosition(bg2.getPositionX() + bg2.getWidth(), 0);
                } else if (bg2.getPositionX() <= -CANVAS_WIDTH) {
                    bg2.setPosition(bg1.getPositionX() + bg1.getWidth(), 0);
                }

                // Ghost rendering and physics
                ghost.addVelocity(0, deltaTime * gravity);

                if (ghost.getPositionY() < 0) {
                    ghost.setVelocity(0, deltaTime * gravity);
                    ghost.setPosition(CANVAS_WIDTH / 2, 0);
                }

                if (ghost.getPositionY() + ghost.getHeight() > CANVAS_HEIGHT) {
                    ghost.setVelocity(0, -speed);
                    ghost.setPosition(CANVAS_WIDTH / 2, CANVAS_HEIGHT - ghost.getHeight());
                }

                ghost.update(deltaTime);
                ghost.render(graphics);

                // Obstacle rendering
                for (Sprite obstacle : obstacles) {
                    obstacle.update(deltaTime);
                    obstacle.render(graphics);
                }

                lastTime = now;
            }

        };
        gameloop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
