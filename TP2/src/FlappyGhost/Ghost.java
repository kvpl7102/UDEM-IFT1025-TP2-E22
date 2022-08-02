package FlappyGhost;

import javafx.scene.image.Image;

public class Ghost {
    private Sprite ghostSprite;
    private Image image;

    public Ghost() {
        ghostSprite = new Sprite();
        image = new Image("./Images/ghost.png");
        ghostSprite.setImage(image);
    }

    public Image getImage() {
        return image;
    }

    public Sprite getGhost() {
        return ghostSprite;
    }
}
