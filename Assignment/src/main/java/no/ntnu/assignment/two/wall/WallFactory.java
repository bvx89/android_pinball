package no.ntnu.assignment.two.wall;

import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public abstract class WallFactory {
    public final static int LEFT = 0;
    public final static int RIGHT = 1;
    public final static int CENTER = 2;

    public static Wall createWall(int wall, Image image, int width, int height, int gridSize) throws IllegalArgumentException {


        switch (wall) {
            case LEFT:
                return new LeftWall(image, width, height, gridSize);
            case RIGHT:
                return new RightWall(image, width, height, gridSize);
            case CENTER:
                return new CenterWall(image, width, height, gridSize);
            default:
                throw new IllegalArgumentException();

        }
    }
}
