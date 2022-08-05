package FlappyGhost;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background extends Sprite {
    private Image image;

    public Background() {
        image = new Image("./Images/bg.png");
        this.setImage(image);
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void render(GraphicsContext graphics) {
        graphics.drawImage(image, this.getPositionX(), this.getPositionY());

    }
}
