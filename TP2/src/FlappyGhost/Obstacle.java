package FlappyGhost;

import java.io.File;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Obstacle extends Sprite {

    Random random = new Random();

    private final int CANVAS_WIDTH = 640;
    private final int CANVAS_HEIGHT = 400;
    private final int speed = 120;

    // private Sprite this;
    private Image image;
    private double obstacleType;
    private boolean isGhostPassed = false;

    public Obstacle(double obstacleType) {

        this.obstacleType = obstacleType;

        File dir = new File("src/Images/obstacles");
        File[] images = dir.listFiles();

        image = new Image(images[random.nextInt(images.length)].getAbsolutePath());
        this.setImage(image);

        setObstacle();
    }

    public double getObstacleType() {
        return obstacleType;
    }

    public void setPassed() {
        isGhostPassed = true;
    }

    public boolean isPassed() {
        return isGhostPassed;
    }

    public void setObstacle() {
        int randHeightPos = (int) Math.floor(CANVAS_HEIGHT - this.getHeight());

        this.setPosition(CANVAS_WIDTH, random.nextInt(randHeightPos));
        this.setVelocity(-speed, 0);

    }

    @Override
    public void render(GraphicsContext graphics) {
        graphics.drawImage(image, this.getPositionX(), this.getPositionY());

    }
}
