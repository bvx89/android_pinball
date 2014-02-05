package no.ntnu.assignment.two.wall;

import no.ntnu.assignment.two.Config;
import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public abstract class WallFactory {
    public final static int LEFT = 0;
    public final static int RIGHT = 1;
    public final static int CENTER = 2;

    public static Wall createWall(int wall, Image image) throws IllegalArgumentException {
        switch (wall) {
            case LEFT:
                return new LeftWall(image,
                                    Config.WINDOW_WIDTH,
                                    Config.WINDOW_HEIGHT,
                                    Config.GRID_SIZE);
            case RIGHT:
                return new RightWall(image,
                                    Config.WINDOW_WIDTH,
                                    Config.WINDOW_HEIGHT,
                                    Config.GRID_SIZE);
            case CENTER:
                return new CenterWall(image,
                                    Config.WINDOW_WIDTH,
                                    Config.WINDOW_HEIGHT,
                                    Config.GRID_SIZE);
            default:
                throw new IllegalArgumentException();

        }
    }
}
