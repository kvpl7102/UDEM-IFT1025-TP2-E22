package FlappyGhost;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Create a ghost object that extends the Entity parent class
 * that player controls in the game
 * 
 */
public class Ghost extends Entity {

    private Image image;
    private double ghostRadius = 30;

    /**
     * Set up the image of the ghost
     * 
     */
    public Ghost() {
        image = resizeImage("./Images/ghost.png", ghostRadius * 2, ghostRadius * 2);
        this.setImage(image);
    }

    /**
     * Getter for the image of the ghost
     *
     * @return the image of the ghost
     * 
     */
    public Image getImage() {
        return image;
    }

    @Override
    public void render(GraphicsContext graphics, boolean isDebug) {

        if (isDebug) { // if in debug mode, render the ghost as a circle with black fill
            graphics.setFill(Color.BLACK);
            graphics.fillOval(this.getPositionX(), this.getPositionY(), ghostRadius, ghostRadius);
        }
        if (!isDebug) { // if not in debug mode, render the image of the ghost
            graphics.drawImage(image, this.getPositionX(), this.getPositionY());
        }

    }

}
