package no.ntnu.assignment.two.model;

import android.util.Log;

import no.ntnu.assignment.two.Config;
import sheep.graphics.Image;

/**
 * Created by bvx89 on 2/4/14.
 */
public class Robot extends Paddle implements PositionListener {

    public Robot(Image image, boolean onTop) {
        super(image, onTop);

        setSpeed(Config.ROBOT_SPEED, 0);
    }

    @Override
    public void notifyPosition(float x, float y) {
        // If ball is to the left
        if (x < getX() + Config.PADDLE_WIDTH / 2) {

            // Left wall
            if (getX() < Config.GRID_SIZE) {
                setXSpeed(Config.ROBOT_SPEED);

            } else {
                setXSpeed(Config.ROBOT_SPEED * -1);
            }

        // If ball is to the right
        } else if(x > getX() + Config.PADDLE_WIDTH / 2) {

            // Right wall
            if (getX() + Config.PADDLE_WIDTH >
                Config.BOARD_WIDTH - Config.GRID_SIZE* 4) {

                setXSpeed(Config.ROBOT_SPEED * -1);

            } else {
                setXSpeed(Config.ROBOT_SPEED);

            }

        // Ball is in the normal of the paddle
        } else {
            setXSpeed(0);
        }
    }
}
