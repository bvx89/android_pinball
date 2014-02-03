package no.ntnu.assignment.two.wall;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public class LeftWall extends Wall {
    public LeftWall(Image image, int width, int height) {
        super(image, width, height);

        setScale(mLineWidth, mHeight);
        setPosition(0, 0); //TODO fix
    }
}
