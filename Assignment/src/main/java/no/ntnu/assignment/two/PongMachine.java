package no.ntnu.assignment.two;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import no.ntnu.assignment.two.model.Ball;
import no.ntnu.assignment.two.model.BallPositionListener;
import no.ntnu.assignment.two.model.Paddle;
import no.ntnu.assignment.two.model.Player;
import no.ntnu.assignment.two.model.Robot;
import no.ntnu.assignment.two.wall.Wall;
import no.ntnu.assignment.two.wall.WallFactory;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * Created by bvx89 on 1/31/14.
 *
 * The pong machine will only run one at the time.
 * If other classes needs it, they will get the
 * current information for an ongoing game
 */
public class PongMachine extends State implements TouchListener {
    // Singleton instance
    private static PongMachine instance;

    // Game State
    private GameState state;

    // Sprites
    private ArrayList<Wall> mWalls = new ArrayList<>();
    private Paddle mPaddleOne;
    private Paddle mPaddleTwo;
    private Ball mBall;

    // Constants for this game
    private static final float PERCENTAGE = 0.01f;
    private static final int PADDLE_WIDTH = 60;

	// Scores
	private TextButton topScore;
	private TextButton bottomScore;


    private PongMachine() {
        super.addTouchListener(this);

        // Calculate the line width/heights
        float lineWidth  = MainActivity.WINDOW_WIDTH  * PERCENTAGE;
        float lineHeight = MainActivity.WINDOW_HEIGHT * PERCENTAGE;


        // Choose the largest to be the new grid size
        int gridSize = (int)(lineHeight > lineWidth ? lineHeight : lineWidth);

		// Add color for writing scores
		Paint[] ButtonColors = {
				new Font(255, 255, 255, 50.0f,
						Typeface.SANS_SERIF, Typeface.BOLD),
				new Font(57, 152, 249, 50.0f,
						Typeface.SANS_SERIF, Typeface.BOLD)
		};
		// Creates score labels
		topScore = new TextButton(2 * gridSize, MainActivity.WINDOW_HEIGHT / 2 - gridSize - 5,"0", ButtonColors);
		bottomScore = new TextButton(2 * gridSize, MainActivity.WINDOW_HEIGHT / 2 + 50,"0", ButtonColors);

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
        /*
        mPaddleTwo = new Player(img,
                boardWidth,
                MainActivity.WINDOW_HEIGHT,
                gridSize,
                PADDLE_WIDTH,
                true);
        */

        mPaddleTwo = new Robot(img,
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

        reset();
    }

    public void newRound() {
        // Set new random X-Speed for the ball
        float speedX = (float)Math.random() * 360 - 180;

        // Set new Y-speed depending on who's starting
        float speedY = (state.isPlayerOneStarting() ? -230f : 230f);

        // Calculate the ball placement
        float posX = MainActivity.WINDOW_WIDTH / 2;
        float posY = MainActivity.WINDOW_HEIGHT / 2;

        // Set it
        mBall.setSpeed(speedX, speedY);
        mBall.setPosition(posX, posY);


    }

    public void reset() {
        state = new GameState();

        mPaddleOne.resetPosition();
        mPaddleTwo.resetPosition();

		topScore.setLabel("0");
		bottomScore.setLabel("0");

        newRound();
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

        // Notify if PaddleOne is robot
        if (mPaddleOne instanceof Robot) {
            ((Robot) mPaddleOne).notifyPosition(mBall.getX(), mBall.getY());
        }

        // Notify if PaddleTwo is robot
        if (mPaddleTwo instanceof Robot) {
            ((Robot) mPaddleTwo).notifyPosition(mBall.getX(), mBall.getY());
        }

		if(mBall.collides(mPaddleOne)){
			mBall.handlePaddleCollision(mPaddleOne);
		}else if(mBall.collides(mPaddleTwo)){
			mBall.handlePaddleCollision(mPaddleTwo);
		}

		if(mBall.getY() > MainActivity.WINDOW_HEIGHT){
			topScore.setLabel("" + (Integer.parseInt(topScore.getLabel()) + 1));
            state.setPlayerOneStarting(false);
			newRound();
		}else if(mBall.getY() + mBall.getScale().getY() < 0){
			bottomScore.setLabel("" + (Integer.parseInt(bottomScore.getLabel()) + 1));
            state.setPlayerOneStarting(true);
			newRound();
		}


		for (Wall w : mWalls) {
			w.update(dt);
			if(mBall.collides(w))
				mBall.handleWallCollision(w);
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

		topScore.draw(canvas);
		bottomScore.draw(canvas);
    }


    /*
     * Handling all touch-events to replicate
     * the movement inside of pong
     */
    @Override
    public boolean onTouchDown(MotionEvent event) {
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
    public boolean onTouchUp(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {
        // Event is on top if it's in the upper bound of the screen
        boolean isTop = event.getY() < MainActivity.WINDOW_HEIGHT / 2;

        // Send event to PaddleOne if in the right section
        if (mPaddleOne instanceof TouchListener && mPaddleOne.isOnTop() == isTop) {
            return ((Player) mPaddleOne).onTouchMove(event);
        }

        // Send event to PaddleTwo if in the right section
        if (mPaddleTwo instanceof TouchListener && mPaddleTwo.isOnTop() == isTop) {
            return ((Player) mPaddleTwo).onTouchMove(event);
        }

        return false;
    }
}
