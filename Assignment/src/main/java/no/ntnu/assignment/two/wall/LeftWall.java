package no.ntnu.assignment.two.wall;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public class LeftWall extends Wall {
    public LeftWall(Image image, int width, int height, int gridSize) {
        super(image, width, height, gridSize);

        setPosition(mGridSize / 2 - 5, 0);
        setScale(mGridSize, mHeight);
    }
}
