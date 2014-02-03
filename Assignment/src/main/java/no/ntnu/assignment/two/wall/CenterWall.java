package no.ntnu.assignment.two.wall;

import android.graphics.Canvas;
import android.util.Log;

import sheep.graphics.Image;

/**
 * Created by bvx89 on 1/31/14.
 */
public class CenterWall extends Wall {

    public CenterWall(Image image, int width, int height) {
        super(image, width, height);

        setScale(mWidth, mLineHeight);
        setPosition(0, mHeight / 2 - getScale().getY());


        Log.d("RIGHT", "scale:" + getScale());
        Log.d("RIGHT", "position:" + getPosition());
    }
}
