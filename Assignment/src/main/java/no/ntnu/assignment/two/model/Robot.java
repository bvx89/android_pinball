package no.ntnu.assignment.two.model;

import android.util.Log;

import sheep.graphics.Image;

/**
 * Created by bvx89 on 2/4/14.
 */
public class Robot extends Paddle implements BallPositionListener {
    private static final float SPEED = 200f;

    public Robot(Image image, int boardWidth, int boardHeight, int gridSize, int width, boolean onTop) {
        super(image, boardWidth, boardHeight, gridSize, width, onTop);

        setSpeed(SPEED, 0);
    }


    @Override
    public void notifyPosition(float x, float y) {
        // Get current speed
        float speedX = getSpeed().getX();

        // If ball is to the left
        if (x < getX() + mWidth / 2) {
            if (getX() < mGridSize) { // Left wall
                setXSpeed(SPEED);
            } else {
                setXSpeed(SPEED * -1);
            }

        // If ball is to the right
        } else if(x > getX() + mWidth / 2) {
            if (getX() + mWidth > mBoardWidth + mGridSize) {
                setXSpeed(SPEED * -1);
            } else {
                setXSpeed(SPEED);
            }

        // Ball is in the normal of the paddle
        } else {
            setXSpeed(0);
        }
    }
}
