import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TP2 extends Application {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 440;

    @Override
    public void start(Stage stage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Background image
        // ImageView background1 = new
        // ImageView(getClass().getResource(".\\Images\\bg.png").toExternalForm());
        // ImageView background2 = new
        // ImageView(getClass().getResource(".\\Images\\bg.png").toExternalForm());
        // root.getChildren().add(background1);
        // root.getChildren().add(background2);

        // TranslateTransition transition1 = new
        // TranslateTransition(Duration.millis(10000), background1);
        // transition1.setFromX(0);
        // transition1.setToX(1 * WIDTH);
        // transition1.setInterpolator(Interpolator.LINEAR);

        // TranslateTransition transition2 = new
        // TranslateTransition(Duration.millis(10000), background2);
        // transition2.setFromX(-1 * WIDTH);
        // transition2.setToX(0);

        // transition2.setInterpolator(Interpolator.LINEAR);

        // ParallelTransition parTrans = new ParallelTransition(transition1,
        // transition2);
        // parTrans.setCycleCount(Animation.INDEFINITE);
        // parTrans.play();

        Canvas canvas = new Canvas(WIDTH, HEIGHT - 40);
        root.getChildren().add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.fillOval(WIDTH / 2, HEIGHT / 2, 30, 30);
        // Object ghost = new Object(WIDTH / 2, HEIGHT / 2, 30);

        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.SPACE) {
                // ghost.fly();
            }
        });

        AnimationTimer gameLoop = new AnimationTimer() {

            long lastTime = 0;

            @Override
            public void handle(long now) {

                if (lastTime == 0) {
                    lastTime = now;
                }
                double delta = (now - lastTime) * 1e-9;

                // ghost.update(delta);

                context.clearRect(0, 0, WIDTH, HEIGHT);
                // context.fillOval(ghost.getXPos(), ghost.getYPos(), ghost.getR(),
                // ghost.getR());

                lastTime = now;

            }

        };

        gameLoop.start();

        // Icon image
        Image icon = new Image(".\\Images\\ghost.png");
        stage.getIcons().add(icon);

        // Image image = new Image(".\\Images\\bg.png");
        // ImageView imageView = new ImageView(image);
        // BackgroundImage backgroundImage = new BackgroundImage(image,
        // BackgroundRepeat.REPEAT,
        // BackgroundRepeat.NO_REPEAT,
        // BackgroundPosition.DEFAULT,
        // BackgroundSize.DEFAULT);
        // root.setBackground(new Background(backgroundImage));

        // Stage setup
        stage.setOnCloseRequest((event) -> {
            Platform.exit();
        });
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Flappy Ghost");
        stage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}