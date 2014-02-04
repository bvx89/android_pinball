package no.ntnu.assignment.two.model;

import android.graphics.Canvas;
import android.util.Log;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by bvx89 on 2/4/14.
 */
public abstract class Paddle extends Sprite {
    protected int mBoardWidth;
    protected int mBoardHeight;
    protected int mGridSize;
    protected int mWidth;

    protected boolean mOnTop;
    private static final String TAG = "TASDAD";

    public Paddle(Image image, int boardWidth, int boardHeight, int gridSize, int width, boolean onTop) {
        super(image);
        mBoardWidth = boardWidth;
        mBoardHeight = boardHeight;
        mGridSize = gridSize;
        mWidth = width;

        mOnTop = onTop;

        setScale(width, gridSize);

        float yPos = (onTop ? gridSize * 3 : boardHeight - gridSize * 5);
        setPosition((gridSize * 2 + boardWidth) / 2 - width/2, yPos);

        Log.d(TAG, "Pos X: " + getX() + ", Pos y: " + getY());
        Log.d(TAG, "Scale X: " + getScale().getX() + ", Scale y: " + getScale().getY());
    }

    public boolean isOnTop() {
        return mOnTop;
    }


}
