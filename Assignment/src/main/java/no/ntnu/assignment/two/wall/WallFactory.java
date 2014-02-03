package no.ntnu.assignment.two.wall;

import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public abstract class WallFactory {
    public final static int LEFT = 0;
    public final static int RIGHT = 1;
    public final static int CENTER = 2;

    public static Wall createWall(int wall, Image image, int width, int height) throws IllegalArgumentException {


        switch (wall) {
            case LEFT:
                return new LeftWall(image, width, height);
            case RIGHT:
                return new RightWall(image, width, height);
            case CENTER:
                return new CenterWall(image, width, height);
            default:
                throw new IllegalArgumentException();

        }
    }
}
