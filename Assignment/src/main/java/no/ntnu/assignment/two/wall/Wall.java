package no.ntnu.assignment.two.wall;

import android.graphics.Canvas;
import android.graphics.Matrix;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public abstract class Wall extends Sprite {
    protected Image mImage;
    protected int mWidth;
    protected int mHeight;

    protected static float mLineWidth;
    protected static float mLineHeight;
    private final static float PERCENTAGE = 0.01f;

    public Wall(Image image, int width, int height) {
        mImage = image;
        mWidth = width;
        mHeight = height;

        mLineWidth  = width  * PERCENTAGE;
        mLineHeight = height * PERCENTAGE;
    }

}
