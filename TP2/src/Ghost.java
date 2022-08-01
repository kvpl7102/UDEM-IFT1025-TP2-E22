import javafx.scene.image.Image;

public class Ghost {
    private Sprite ghostSprite;
    private double positionX;
    private double positionY;
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
