package no.ntnu.assignment.two.model;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import no.ntnu.assignment.two.Config;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.input.TouchListener;

/**
 * Created by bvx89 on 2/4/14.
 */
public class Player extends Paddle implements TouchListener {

    public Player(Image image, boolean OnTop) {
        super(image, OnTop);
    }

    public void handleMove(float moveX) {
        float newX;
        if (moveX - Config.PADDLE_WIDTH < Config.GRID_SIZE * 2) { // Left
            newX = Config.GRID_SIZE*2;
        } else if (moveX + Config.PADDLE_WIDTH > Config.BOARD_WIDTH) { // Right
            newX = Config.BOARD_WIDTH - Config.PADDLE_WIDTH * 2 + 1;
        } else {
            newX = moveX - Config.PADDLE_WIDTH - 1;
        }

        setPosition(newX, getY());

    }

    @Override
    public boolean onTouchDown(MotionEvent event) {
        // Be sure that the touch is on the right side of the field
        if (getY() > Config.WINDOW_HEIGHT && !mOnTop ||
            getY() < Config.WINDOW_HEIGHT && !mOnTop) {

            handleMove(event.getX());
            return true;
        }

        return false;
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {
        onTouchDown(event);
        return true;
    }
}
