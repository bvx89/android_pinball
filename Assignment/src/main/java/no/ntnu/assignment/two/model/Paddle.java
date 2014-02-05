package no.ntnu.assignment.two.model;

import android.graphics.Canvas;
import android.util.Log;

import no.ntnu.assignment.two.Config;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by bvx89 on 2/4/14.
 */
public abstract class Paddle extends Sprite {
    protected boolean mOnTop;
    private static final String TAG = "TASDAD";

    public Paddle(Image image, boolean onTop) {
        super(image);

        mOnTop = onTop;



        Log.d(TAG, "Pos X: " + getX() + ", Pos y: " + getY());
        Log.d(TAG, "Scale X: " + getScale().getX() + ", Scale y: " + getScale().getY());
    }

    public boolean isOnTop() {
        return mOnTop;
    }

    public void resetPosition() {
        setScale(Config.PADDLE_WIDTH, Config.GRID_SIZE);

        float yPos = (mOnTop ? Config.GRID_SIZE * 3 :
                                Config.WINDOW_HEIGHT - Config.GRID_SIZE * 5);

        setPosition((Config.GRID_SIZE * 2 + Config.BOARD_WIDTH) / 2 - Config.PADDLE_WIDTH/2, yPos);
    }

}
