package no.ntnu.assignment.two.model;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.input.TouchListener;

/**
 * Created by bvx89 on 2/4/14.
 */
public class Player extends Paddle implements TouchListener {

    public Player(Image image, int boardWidth, int boardHeight, int gridSize, int width, boolean OnTop) {
        super(image, boardWidth, boardHeight, gridSize, width, OnTop);
    }

    public void handleMove(float moveX) {
        float newX;
        if (moveX - mWidth < mGridSize*2) { // Left
            newX = mGridSize*2;
        } else if (moveX + mWidth > mBoardWidth) { // Right
            newX = mBoardWidth - mWidth * 2 + 1;
        } else {
            newX = moveX - mWidth - 1;
        }

        setPosition(newX, getY());
    }

    @Override
    public boolean onTouchDown(MotionEvent event) {
        handleMove(event.getX());
        return true;
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {
        handleMove(event.getX());
        return true;
    }
}
