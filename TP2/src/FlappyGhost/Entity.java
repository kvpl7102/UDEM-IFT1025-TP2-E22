package FlappyGhost;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * An abstract class that represents and holds informations
 * of any game object in the game
 * 
 */
public abstract class Entity {
    private double positionX, positionY;
    private double velocityX, velocityY;
    private double width;
    private double height;
    private Image image;
    private Rectangle2D boundary;

    /**
     * Create a entity with default X-pos, Y-pos, X-velocity and Y-velocity
     * 
     */
    public Entity() {
        this.positionX = 0;
        this.positionY = 0;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    /**
     * Getter of the X-position of object
     * 
     * @return the X-coordinate of object
     * 
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * Getter of the Y-position of object
     * 
     * @return Y-coordinate of object
     * 
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * Getter of the width of object
     * 
     * @return the width of the image representing object
     * 
     */
    public double getWidth() {
        return width;
    }

    /**
     * Getter of the height of object
     * 
     * @return the height of the image representing object
     * 
     */
    public double getHeight() {
        return height;
    }

    /**
     * Getter of the velocity in the X-axis of object
     * 
     * @return the speed in the X-axis of object
     * 
     */
    public double getVelocityX() {
        return this.velocityX;
    }

    /**
     * Getter of the velocity in the X-axis of object
     * 
     * @return the speed in the X-axis of object
     * 
     */
    public double getVelocityY() {
        return this.velocityY;
    }

    /**
     * @return a rectangle2D representing the boundary of object
     * 
     */
    public Rectangle2D getBoundary() {
        boundary = new Rectangle2D(positionX, positionY, width, height);
        return boundary;
    }

    /**
     * @return the image representing current object
     * 
     */
    public Image getImage() {
        return image;
    }

    /**
     * Set the image of object
     * 
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /**
     * Resized the image with desired width and height
     * 
     * @param path
     * @param width
     * @param height
     * @return the resized image with width and height in the parameters
     * 
     */
    public Image resizeImage(String path, double width, double height) {
        Image resizedImage = new Image(path, width, height, false, false);
        return resizedImage;
    }

    /**
     * Set the position for the object
     * 
     * @param positionX position in the X-axis
     * @param positionY position in the Y-axis
     * 
     */
    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Set the position in the X-axis
     * 
     * @param positionX
     * 
     */
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    /**
     * Set the position in the Y-axis
     * 
     * @param positionY
     * 
     */
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    /**
     * Set the velocity of the object
     * 
     * @param velocityX velocity in the X-axis
     * @param velocityY velocity in the Y-axis
     */
    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * Set the velocity in the Y-axis
     * 
     * @param velocityY
     * 
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Set the velocity in the x-axis
     * 
     * @param velocityX
     * 
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * Check if this object overlaps with other object
     * 
     * @param other
     * @return a boolean value to determine whether this object overlaps with object
     *         passed in as parameter
     * 
     */
    public boolean overlaps(Entity other) {
        return other.getBoundary().intersects(this.getBoundary());
    }

    /**
     * Add value of x and y to the velocity
     * 
     * @param x velocity in
     * @param y
     * 
     */
    public void addVelocity(double x, double y, boolean gamePaused) {
        if (!gamePaused) {
            this.velocityX += x;
            this.velocityY += y;
        }

    }

    /**
     * Update position of object after elapsed time
     * 
     * @param deltaTime
     */
    public void update(double deltaTime, boolean gamePaused) {
        if (!gamePaused) {
            positionX += deltaTime * velocityX;
            positionY += deltaTime * velocityY;
        }

    }

    /**
     * Render object in the canvas depends on the state of the game
     * 
     * @param graphics
     * @param isDebug  check if the game is in debug mode or not
     */
    public abstract void render(GraphicsContext graphics, boolean isDebug);

}
