package no.ntnu.assignment.two;

import android.graphics.Paint;
import android.graphics.Typeface;

import sheep.graphics.Font;

/**
 * Created by bvx89 on 2/5/14.
 */
public abstract class Config {
    // Game Board
    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;
    public static int GRID_SIZE;
    public static int BOARD_WIDTH;

    // Game constants
    public static final float PERCENTAGE = 0.01f;
    public static final int PADDLE_WIDTH = 60;
    public static final float ROBOT_SPEED = 200f;
    public static final float BALL_SPEED_INCREASE = 1.1f;

    // Font used for writing scores
    public static final Paint[] FONT_SCORE = {
            new Font(255, 255, 255, 50.0f,
                    Typeface.SANS_SERIF, Typeface.BOLD),
            new Font(57, 152, 249, 50.0f,
                    Typeface.SANS_SERIF, Typeface.BOLD)
    };
}
