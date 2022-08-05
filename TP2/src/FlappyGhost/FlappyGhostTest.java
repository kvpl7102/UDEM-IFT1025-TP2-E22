package FlappyGhost;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlappyGhostTest extends Application {

    public final static int WINDOW_WIDTH = 640;
    public final static int WINDOW_HEIGHT = 440;
    private final int CANVAS_WIDTH = 640;
    private final int CANVAS_HEIGHT = 400;
    private int speed = 120;
    private int gravity = 500;
    private int score = 0;
    private boolean isPaused;
    private boolean isPassed = false;

    private Timer timer;
    private GraphicsContext graphics;
    private AnimationTimer gameloop;

    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ImageView background1, background2;
    private Ghost ghost;
    private Background bg1, bg2;
    private Text scoreLabel;

    Random random = new Random();

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

        BorderPane root = new BorderPane();
        Group canvas = createCanvas();

        HBox controller = new HBox(10);
        controller.setMaxHeight(WINDOW_HEIGHT - CANVAS_HEIGHT);
        controller.setMaxWidth(WINDOW_WIDTH);
        controller.setAlignment(Pos.CENTER);
        controller.setPadding(new Insets(5));

        Button pauseBtn = new Button("Pause");
        Button resumeBtn = new Button("Resume");
        CheckBox debugCheck = new CheckBox("Debug Mode");
        scoreLabel = new Text("Point: " + score);

        ObservableList<Node> children = controller.getChildren();
        children.addAll(pauseBtn, debugCheck, scoreLabel);

        pauseBtn.setOnMousePressed((e) -> {
            isPaused = true;
            gravity = 0;
            speed = 0;
            gameloop.stop();
            bg1.setVelocity(0, 0);
            bg2.setVelocity(0, 0);

            children.set(0, resumeBtn);
        });

        resumeBtn.setOnMousePressed((e) -> {
            isPaused = false;
            gravity = 500;
            speed = 120;
            gameloop.start();
            bg1.setVelocity(-speed, 0);
            bg2.setVelocity(-speed, 0);
            children.set(0, pauseBtn);
        });

        root.getChildren().add(canvas);
        root.setBottom(controller);
        BorderPane.setAlignment(controller, Pos.CENTER);

        Platform.runLater(() -> {
            canvas.requestFocus();
        });

        root.setOnMouseClicked((e) -> {
            canvas.requestFocus();
        });
        return root;
    }

    private Group createCanvas() {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        graphics = canvas.getGraphicsContext2D();

        timer = new Timer(true);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                createNewObstacle();
            }
        }, 1000, 3000);

        createGhost();
        createBackground();

        root.getChildren().addAll(canvas);
        return root;
    }

    private Group createController() {
        Group root = new Group();

        HBox controller = new HBox(10);
        controller.setMaxHeight(WINDOW_HEIGHT - CANVAS_HEIGHT);
        controller.setMaxWidth(WINDOW_WIDTH);
        controller.setAlignment(Pos.CENTER);
        controller.setPadding(new Insets(5));

        Button pauseBtn = new Button("Pause");
        Button resumeBtn = new Button("Resume");
        CheckBox debugCheck = new CheckBox("Debug Mode");
        Text scoreLabel = new Text("Point: " + score);

        ObservableList<Node> children = controller.getChildren();
        children.addAll(pauseBtn, debugCheck, scoreLabel);

        pauseBtn.setOnAction((e) -> {
            isPaused = true;
            children.set(0, resumeBtn);
        });
        resumeBtn.setOnAction((e) -> {
            isPaused = false;
            children.set(0, pauseBtn);
        });

        root.getChildren().add(controller);

        return root;
    }

    private void createBackground() {

        // background1 = new ImageView(new Image("./Images/bg.png"));
        // background2 = new ImageView(new Image("./Images/bg.png"));

        // TranslateTransition transition1 = new
        // TranslateTransition(Duration.millis(5000), background1);
        // transition1.setFromX(1 * CANVAS_WIDTH);
        // transition1.setToX(0);
        // transition1.setInterpolator(Interpolator.LINEAR);

        // TranslateTransition transition2 = new
        // TranslateTransition(Duration.millis(5000), background2);
        // transition2.setFromX(0);
        // transition2.setToX(-1 * CANVAS_WIDTH);
        // transition2.setInterpolator(Interpolator.LINEAR);

        // ParallelTransition parTrans = new ParallelTransition(transition1,
        // transition2);
        // parTrans.setCycleCount(Animation.INDEFINITE);

        // parTrans.play();

        bg1 = new Background();
        bg1.setImage(new Image("./Images/bg.png"));
        bg1.setVelocity(-speed, 0);

        bg2 = new Background();
        bg2.setImage(new Image("./Images/bg.png"));
        bg2.setVelocity(-speed, 0);
        bg2.setPosition(bg1.getWidth(), 0);

    }

    private void createGhost() {
        ghost = new Ghost();
        ghost.setPosition(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
    }

    private void createNewObstacle() {
        Obstacle newObstacle = new Obstacle(Math.round(Math.random() * 1));
        // Obstacle newObstacle = new Obstacle(0);
        obstacles.add(newObstacle);
    }

    public void startGame() {

        gameloop = new AnimationTimer() {
            long lastTime = 0;

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

                if (ghost.getVelocityY() < -300) {
                    ghost.setVelocityY(-300);
                }

                // Obstacle rendering
                for (Obstacle obstacle : obstacles) {

                    switch ((int) obstacle.getObstacleType()) {
                        case 0:
                            obstacle.update(deltaTime);
                            obstacle.render(graphics);
                            break;

                        case 1:
                            obstacle.update(deltaTime);
                            double sin = Math.sin(obstacle.getPositionX() * 0.1) * 4;
                            obstacle.setPositionY(sin + obstacle.getPositionY());
                            obstacle.render(graphics);
                            break;
                    }

                    // Calculate point if ghost passes the object
                    if (obstacle.getPositionX() < ghost.getPositionX() && !obstacle.isPassed()) {
                        obstacle.setPassed();
                        score += 5;
                    }
                }

                scoreLabel.setText("Point " + score);

                lastTime = now;

            }

        };
        gameloop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
