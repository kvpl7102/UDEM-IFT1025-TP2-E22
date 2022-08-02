package FlappyGhost;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private double positionX, positionY;
    private double velocityX, velocityY;
    private double width;
    private double height;
    private Image image;
    private Rectangle2D boundary;

    public Sprite() {
        this.positionX = 0;
        this.positionY = 0;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    // Getters methods

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getVelocityX() {
        return this.velocityX;
    }

    public double getVelocityY() {
        return this.velocityY;
    }

    public Rectangle2D getBoundary() {
        boundary = new Rectangle2D(positionX, positionY, width, height);
        return boundary;
    }

    public Image getImage() {
        return image;
    }

    // Setters methods

    public void setImage(Image image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * Add value of x and y to the velocity of a sprite
     * 
     * @param x
     * @param y
     * 
     */
    public void addVelocity(double x, double y) {
        this.velocityX += x;
        this.velocityY += y;
    }

    /**
     * Update position of a sprite after deltaTime elapsed
     * 
     * @param deltaTime
     */
    public void update(double deltaTime) {
        positionX += deltaTime * velocityX;
        positionY += deltaTime * velocityY;
    }

    public void render(GraphicsContext graphics) {
        graphics.drawImage(image, positionX, positionY);
    }
}
