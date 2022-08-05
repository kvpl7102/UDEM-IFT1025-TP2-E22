package FlappyGhost;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ghost extends Sprite {

    private Image image;

    public Ghost() {
        image = new Image("./Images/ghost.png");
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
