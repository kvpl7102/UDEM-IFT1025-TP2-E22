package FlappyGhost;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Cretet a background object that extends the Entity parent class
 * 
 */
public class Background extends Entity {
    private Image image;

    /**
     * Set the image for the background
     * 
     */
    public Background() {
        image = new Image("./Images/bg.png");
        this.setImage(image);
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void render(GraphicsContext graphics, boolean isDebug) {
        graphics.drawImage(image, this.getPositionX(), this.getPositionY());
    }
}
