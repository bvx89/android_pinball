package no.ntnu.assignment.two.wall;

import android.graphics.Canvas;
import android.graphics.Matrix;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public abstract class Wall extends Sprite {
    protected int mWidth;
    protected int mHeight;

    protected static int mGridSize;

    public Wall(Image image, int width, int height, int gridSize) {
        super(image);
        mWidth = width;
        mHeight = height;

        mGridSize = gridSize;

    }

}
