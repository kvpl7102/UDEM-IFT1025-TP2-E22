package FlappyGhost;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * IFT 1025 - E22 - TP2
 * This program creates a game called Flappy Ghost
 * where player needs to control the ghost to travel
 * through the level filled with obstacles. The only goal
 * is to travel as far as possible without touching any obstacle.
 * 
 * @author Le Kinh Vi Phung -20178538
 * @author Adam Belhachmi - 20160421
 * @since 8/8/2022
 * 
 */
public class FlappyGhost extends Application {

    // Variables declaration
    public final static int WINDOW_WIDTH = 640;
    public final static int WINDOW_HEIGHT = 440;
    private final int CANVAS_WIDTH = 640;
    private final int CANVAS_HEIGHT = 400;
    private int speed = 120;
    private int gravity = 500;
    private int score = 0;
    private int obstaclesPassed = 0;
    private boolean gamePaused = false;
    private boolean isDebug;

    private GraphicsContext graphics;
    private AnimationTimer gameloop;
    private Timer timer;

    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Ghost ghost;
    private Background bg1, bg2;
    private Text scoreLabel;
    private CheckBox debugCheckBox;

    Random random = new Random();

    @Override
    public void start(Stage stage) throws Exception {

        // Create content of the game
        Parent root = createParent();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // When player presses SPACE, the ghost jumps
        scene.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.SPACE) {
                ghost.addVelocity(0, -300, gamePaused);
            }
        });

        // Set up the stage
        Image icon = new Image(".\\Images\\ghost.png");
        stage.getIcons().add(icon);
        stage.setOnCloseRequest((event) -> {
            timer.cancel();
            Platform.exit();
        });
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Flappy Ghost");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        startGame(); // start the game
    }

    /**
     * Create parent node of the game
     * 
     * @return a parent node that holds the content of the game
     */
    private Parent createParent() {

        BorderPane root = new BorderPane(); // parent node of the game
        Group canvas = createCanvas(); // canvas to draw images of objects

        // Create the controller section with:
        // 1. A pause / resume button
        // 2. The debug checkbox to toggle debug mode of the game
        // 3. Score display
        HBox controller = new HBox(10);
        controller.setMaxHeight(WINDOW_HEIGHT - CANVAS_HEIGHT);
        controller.setMaxWidth(WINDOW_WIDTH);
        controller.setAlignment(Pos.CENTER);
        controller.setPadding(new Insets(5));

        Button pauseBtn = new Button("Pause");
        Button resumeBtn = new Button("Resume");
        debugCheckBox = new CheckBox("Debug Mode");
        scoreLabel = new Text("Point: " + score);

        ObservableList<Node> children = controller.getChildren();
        children.addAll(pauseBtn, debugCheckBox, scoreLabel);

        pauseBtn.setOnMousePressed((e) -> {
            gamePaused = true;
            children.set(0, resumeBtn);
        });

        resumeBtn.setOnMousePressed((e) -> {
            gamePaused = false;
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

    /**
     * Create a canvas to illustrate the gameplay
     * 
     * @return a group node that holds the canvas of the game
     */
    private Group createCanvas() {
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        graphics = canvas.getGraphicsContext2D();

        // Create new obstacle every 3 seconds
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!gamePaused) {
                    createNewObstacle();
                }
            }
        }, 3000, 3000);

        createGhost();
        createBackground();

        root.getChildren().addAll(canvas);
        return root;
    }

    /**
     * Create a scrolling background
     * 
     */
    private void createBackground() {
        bg1 = new Background();
        bg1.setImage(new Image("./Images/bg.png"));
        bg1.setVelocity(-speed, 0);

        bg2 = new Background();
        bg2.setImage(new Image("./Images/bg.png"));
        bg2.setVelocity(-speed, 0);
        bg2.setPosition(bg1.getWidth(), 0);
    }

    /**
     * Create the ghost object that player controls
     * 
     */
    private void createGhost() {
        ghost = new Ghost();
        ghost.setPosition(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
    }

    /**
     * Create new obstacle with random movement type
     *
     */
    private void createNewObstacle() {
        Obstacle newObstacle = new Obstacle((int) Math.round(Math.random() * 2));
        obstacles.add(newObstacle);
    }

    /**
     * Method to start the game with creation of a game loop
     * 
     */
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

                isDebug = debugCheckBox.isSelected();

                graphics.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

                // Background scrolling
                bg1.render(graphics, false);
                bg2.render(graphics, false);
                bg1.update(deltaTime, gamePaused);
                bg2.update(deltaTime, gamePaused);

                if (bg1.getPositionX() <= -CANVAS_WIDTH) {
                    bg1.setPosition(bg2.getPositionX() + bg2.getWidth(), 0);
                } else if (bg2.getPositionX() <= -CANVAS_WIDTH) {
                    bg2.setPosition(bg1.getPositionX() + bg1.getWidth(), 0);
                }

                // Ghost rendering and physics
                ghost.addVelocity(0, deltaTime * gravity, gamePaused);

                if (ghost.getPositionY() < 0) {
                    ghost.setVelocity(0, deltaTime * gravity);
                    ghost.setPosition(CANVAS_WIDTH / 2, 0);
                }

                if (ghost.getPositionY() + ghost.getHeight() > CANVAS_HEIGHT) {
                    ghost.setVelocity(0, -speed);
                    ghost.setPosition(CANVAS_WIDTH / 2, CANVAS_HEIGHT - ghost.getHeight());
                }

                ghost.update(deltaTime, gamePaused);
                ghost.render(graphics, isDebug);

                // Limit the speed of the ghost
                if (ghost.getVelocityY() < -300) {
                    ghost.setVelocityY(-300);
                }

                // Obstacles rendering
                for (int i = 0; i < obstacles.size(); i++) {

                    switch ((int) obstacles.get(i).getObstacleType()) {
                        case 0: // Obstacle with linear movement

                            if (!gamePaused) {
                                obstacles.get(i).update(deltaTime, gamePaused);
                            }
                            obstacles.get(i).render(graphics, isDebug);
                            break;

                        case 1: // Obstacle with sinus movement

                            if (!gamePaused) {
                                obstacles.get(i).update(deltaTime, gamePaused);
                                double sin = Math.sin(obstacles.get(i).getPositionX() * 0.1) * 4;
                                obstacles.get(i).setPositionY(sin + obstacles.get(i).getPositionY());
                                // obstacles.get(i).render(graphics, isDebug);
                            }
                            obstacles.get(i).render(graphics, isDebug);
                            break;

                        case 2: // Obstacle with quantic movement

                            if (!gamePaused) {

                                obstacles.get(i).update(deltaTime, gamePaused);
                                // Choose a random number between -30 and 30 pixels
                                // to move the obstacles by that variation of pixels
                                int min = -30;
                                int max = 30;
                                int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

                                // Modify the position by adding the random number of pixels selected
                                obstacles.get(i).setPositionX(obstacles.get(i).getPositionX() + random);
                                obstacles.get(i).setPositionY(obstacles.get(i).getPositionY() + random);
                                obstacles.get(i).render(graphics, isDebug);

                                // Reset the inital position of the obstacles so the movement stays controlled
                                obstacles.get(i).setPositionX(obstacles.get(i).getPositionX() - random);
                                obstacles.get(i).setPositionY(obstacles.get(i).getPositionY() - random);

                            } else {
                                obstacles.get(i).render(graphics, isDebug);
                            }

                            break;

                    }

                    // Calculate point if ghost passes the obstacle
                    if (obstacles.get(i).getPositionX() < ghost.getPositionX() && !obstacles.get(i).isPassed()) {
                        score += 5;
                        obstaclesPassed++;
                        obstacles.get(i).setPassed();
                    }

                    // Collision handling
                    if (obstacles.get(i).overlaps(ghost)) {
                        if (!isDebug) { // If not in debug mode, reset the game
                            resetGame();
                        } else { // If in debug mode, display objects as shapes
                            obstacles.get(i).setOverlapped(true);
                        }
                    } else {
                        obstacles.get(i).setOverlapped(false);
                    }

                    // Increase speed for every 2 objects the ghost has passed
                    if (obstaclesPassed != 0 && obstaclesPassed % 2 == 0) {
                        speed += 15;
                        obstaclesPassed = 0;
                        bg1.setVelocity(-speed, 0);
                        bg2.setVelocity(-speed, 0);
                        obstacles.get(i).update(deltaTime, gamePaused);
                    }
                }

                scoreLabel.setText("Point " + score); // Update the score
                lastTime = now;
            }
        };
        gameloop.start();
    }

    /**
     * Reset the game in the initial state
     * 
     */
    private void resetGame() {
        createBackground();
        createGhost();
        obstacles.clear();
        score = 0;
        speed = 120;
        bg1.setVelocity(-speed, 0);
        bg2.setVelocity(-speed, 0);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
