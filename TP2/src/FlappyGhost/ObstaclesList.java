package FlappyGhost;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class ObstaclesList {

    public static ArrayList<Sprite> obstaclesList = new ArrayList<>();

    File files = new File("src/Images/obstacles");

    private static void addFiles(File[] files) {
        for (File file : files) {
            Sprite s = new Sprite();
            s.setImage(new Image(file.getPath()));
            obstaclesList.add(s);
        }
    }

}
