package FlappyGhost;

import java.io.File;
import java.util.Random;

import javafx.scene.image.Image;

public class Obstacle {
    Random random = new Random();

    private final int CANVAS_WIDTH = 640;
    private final int CANVAS_HEIGHT = 400;

    private Sprite obstacleSprite;
    private Image image;

    public Obstacle() {
        obstacleSprite = new Sprite();
        File dir = new File("src/Images/obstacles");
        File[] images = dir.listFiles();

        image = new Image(images[random.nextInt(images.length)].getAbsolutePath());
        obstacleSprite.setImage(image);
    }

    public Sprite getObstacle(int obstacleType) {
        switch (obstacleType) {
            case 1:
                obstacleSprite.setPosition(CANVAS_WIDTH,
                        random.nextInt((int) Math.floor(CANVAS_HEIGHT - obstacleSprite.getHeight())));
                obstacleSprite.setVelocity(-120, 0);
                break;

            // case 2:
            // obstacleSprite.setPosition(positionX, positionY);
            // break;

            // case 3:
            // obstacleSprite.setPosition(positionX, positionY);
            // break;

        }
        return obstacleSprite;
    }
}
