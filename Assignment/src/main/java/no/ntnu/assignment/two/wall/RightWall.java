package no.ntnu.assignment.two.wall;

import android.util.Log;

import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public class RightWall extends Wall {

    public RightWall(Image image, int width, int height, int gridSize) {
        super(image, width, height, gridSize);

        setScale(mGridSize, mHeight);
        setPosition(mWidth - getScale().getX() * 2 + 1, 0); //TODO fix
    }
}
