package no.ntnu.assignment.two;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import no.ntnu.assignment.two.model.Ball;
import no.ntnu.assignment.two.model.Paddle;
import no.ntnu.assignment.two.model.Player;
import no.ntnu.assignment.two.wall.Wall;
import no.ntnu.assignment.two.wall.WallFactory;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

/**
 * Created by bvx89 on 1/31/14.
 *
 * The pong machine will only run one at the time.
 * If other classes needs it, they will get the
 * current information for an ongoing game
 */
public class PongMachine extends State implements TouchListener, CollisionListener {

    private static PongMachine instance;

    // Sprites
    private ArrayList<Wall> mWalls = new ArrayList<>();
    private Paddle mPaddleOne;
    private Paddle mPaddleTwo;
    private Ball mBall;

    private static final float PERCENTAGE = 0.01f;
    private static final int PADDLE_WIDTH = 60;
    private static final String TAG = "Pdsaijdasioj";

    private PongMachine() {
        super.addTouchListener(this);

        // Calculate the line width/heights
        float lineWidth  = MainActivity.WINDOW_WIDTH  * PERCENTAGE;
        float lineHeight = MainActivity.WINDOW_HEIGHT * PERCENTAGE;

        // Choose the largest to be the new grid size
        int gridSize = (int)(lineHeight > lineWidth ? lineHeight : lineWidth);

        // Create the walls and add them to the array
        Image img = new Image(R.drawable.white_pixel);

        for (int i = 0; i < 3; i++) {
            mWalls.add(WallFactory.createWall(
                    i,
                    img,
                    MainActivity.WINDOW_WIDTH,
                    MainActivity.WINDOW_HEIGHT,
                    gridSize)
            );
        }



        // Create player
        int boardWidth = MainActivity.WINDOW_WIDTH - gridSize*2;
        mPaddleOne = new Player(img,
                            boardWidth,
                            MainActivity.WINDOW_HEIGHT,
                            gridSize,
                            PADDLE_WIDTH,
                            false);

        // Create player two
        mPaddleTwo = new Player(img,
                boardWidth,
                MainActivity.WINDOW_HEIGHT,
                gridSize,
                PADDLE_WIDTH,
                true);

        mBall = new Ball(img,
                        gridSize,
                        MainActivity.WINDOW_WIDTH / 2,
                        MainActivity.WINDOW_HEIGHT / 2,
                        MainActivity.WINDOW_HEIGHT);
        mBall.setSpeed(180f,180f);

        mBall.addCollisionListener(this);
    }

    /*
     * Following the Singleton pattern
     */
    public static PongMachine getInstance() {
        if (instance == null) {
            instance = new PongMachine();
        }

        return instance;
    }




    @Override
    public void update(float dt) {
        super.update(dt);

        mBall.update(dt);
        mPaddleOne.update(dt);
        mPaddleTwo.update(dt);

        for (Wall w : mWalls) {
            w.update(dt);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.DKGRAY);

        for (Wall w : mWalls) {
            w.draw(canvas);
        }

        mPaddleOne.draw(canvas);
        mPaddleTwo.draw(canvas);

        mBall.draw(canvas);
    }


    /*
     * Handling all touch-events to replicate
     * the movement inside of pong
     */
    @Override
    public boolean onTouchDown(MotionEvent event) {
        return handleTouch(event);
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {
        return handleTouch(event);
    }

    private boolean handleTouch(MotionEvent event) {

        // Event is on top if it's in the upper bound of the screen
        boolean isTop = event.getY() < MainActivity.WINDOW_HEIGHT / 2;

        // Send event to PaddleOne if in the right section
        if (mPaddleOne instanceof TouchListener && mPaddleOne.isOnTop() == isTop) {
            return ((Player) mPaddleOne).onTouchDown(event);
        }

        // Send event to PaddleTwo if in the right section
        if (mPaddleTwo instanceof TouchListener && mPaddleTwo.isOnTop() == isTop) {
            return ((Player) mPaddleTwo).onTouchDown(event);
        }

        return false;
    }

    @Override
    public void collided(Sprite sprite, Sprite other) {
        if (sprite != mBall) {
            throw new IllegalArgumentException();

        } else {
            if (other instanceof Wall) {
                mBall.handleWallCollision((Wall) other);
            } else if (other instanceof Paddle) {
                mBall.handlePaddleCollision((Paddle) other);
            }
        }
    }
}
