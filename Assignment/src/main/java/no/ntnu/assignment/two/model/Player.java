package no.ntnu.assignment.two.model;

import android.graphics.Canvas;
import android.view.MotionEvent;

import sheep.graphics.Image;
import sheep.input.TouchListener;

/**
 * Created by bvx89 on 2/4/14.
 */
public class Player extends Paddle implements TouchListener {

    public Player(Image image, int boardWidth, int boardHeight, int gridSize, int width, boolean OnTop) {
        super(image, boardWidth, boardHeight, gridSize, width, OnTop);
    }

    public void handleTouch(float touchX) {
        float newX;
        if (touchX - mWidth < mGridSize*2) { // Left
            newX = mGridSize*2;
        } else if (touchX + mWidth > mBoardWidth) { // Right
            newX = mBoardWidth - mWidth * 2 + 1;
        } else {
            newX = touchX - mWidth - 1;
        }

        setPosition(newX, getY());
    }

    @Override
    public boolean onTouchDown(MotionEvent event) {
        handleTouch(event.getX());
        return true;
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {
        handleTouch(event.getX());
        return true;
    }
}
