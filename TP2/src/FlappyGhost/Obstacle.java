package FlappyGhost;

import java.io.File;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Create an obstacle object that extends the Entity parent class
 * 
 */
public class Obstacle extends Entity {

    Random random = new Random();

    private final int CANVAS_WIDTH = 640;
    private final int CANVAS_HEIGHT = 400;
    private final int speed = 120;
    private double obstacleRadius;

    private Image image;
    private int obstacleType;
    private boolean isGhostPassed = false;
    private boolean isOverlapped = false;

    /**
     * Create a new obstacle with random movement type
     * 
     * @param obstacleType type of obstacle movement:
     *                     0 - immobile; 1 - sinus movement;
     *                     2 - quantic movement
     * 
     */
    public Obstacle(int obstacleType) {
        this.obstacleType = obstacleType;

        File dir = new File("src/Images/obstacles");
        File[] images = dir.listFiles();

        obstacleRadius = random.nextInt(60 - 30) + 30;
        image = resizeImage(images[random.nextInt(images.length)].getAbsolutePath(), obstacleRadius,
                obstacleRadius * 1.5);
        this.setImage(image);

        setObstacle();
    }

    /**
     * Getter for the obstacle movement type
     * 
     * @return the number indicating the movement type of obstacle
     * 
     */
    public int getObstacleType() {
        return obstacleType;
    }

    /**
     * Check if the ghost has passed this obstacle or not
     * 
     * @return a boolean value to check if the ghost
     *         has already passed this obstacle
     */
    public boolean isPassed() {
        return isGhostPassed;
    }

    /**
     * Check if this obstacle is overlapped with the ghost
     * 
     * @return a boolean value to check if the ghost
     *         is overlapped with this obstacle
     */
    public boolean isOverlapped() {
        return isOverlapped;
    }

    /**
     * When the ghost has passed this obstacle...
     * 
     */
    public void setPassed() {
        isGhostPassed = true;
    }

    /**
     * Set the collision state of this obstacle with the ghost
     * 
     * @param isOverlapped the collision state of this obstacle to be set
     * 
     */
    public void setOverlapped(boolean isOverlapped) {
        this.isOverlapped = isOverlapped;
    }

    /**
     * Set up position and velocity of the obstacle
     * 
     */
    public void setObstacle() {
        int randHeightPos = (int) Math.floor(CANVAS_HEIGHT - this.getHeight());
        this.setPosition(CANVAS_WIDTH, random.nextInt(randHeightPos));
        this.setVelocity(-speed, 0);

    }

    @Override
    public void render(GraphicsContext graphics, boolean isDebug) {
        if (isDebug) { // if in debug mode, render the obstacle as a circle with yellow fill
            if (!isOverlapped) {
                graphics.setFill(Color.YELLOW);
                graphics.fillOval(this.getPositionX(), this.getPositionY(), obstacleRadius, obstacleRadius);
            } else {
                graphics.setFill(Color.RED);
                graphics.fillOval(this.getPositionX(), this.getPositionY(), obstacleRadius, obstacleRadius);
            }

        }
        if (!isDebug) { // if not in debug mode, render the image of obstacle
            graphics.drawImage(image, this.getPositionX(), this.getPositionY());
        }

    }
}
