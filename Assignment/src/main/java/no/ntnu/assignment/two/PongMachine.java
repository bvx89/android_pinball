package no.ntnu.assignment.two;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.ArrayList;

import no.ntnu.assignment.two.model.Ball;
import no.ntnu.assignment.two.model.Paddle;
import no.ntnu.assignment.two.model.Player;
import no.ntnu.assignment.two.model.Robot;
import no.ntnu.assignment.two.wall.Wall;
import no.ntnu.assignment.two.wall.WallFactory;
import sheep.game.State;
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

	// Scores
	private TextButton scoreTop;
	private TextButton scoreBtm;


    private PongMachine() {
		// Creates score labels
		scoreTop = new TextButton(  Config.GRID_SIZE * 2,
                                    Config.WINDOW_HEIGHT / 2 - Config.GRID_SIZE - 5,
                                    "0",
                                    Config.FONT_SCORE);

		scoreBtm = new TextButton(  Config.GRID_SIZE * 2,
                                    Config.WINDOW_HEIGHT / 2 + 50,
                                    "0",
                                    Config.FONT_SCORE);

        // Get the standard image to be used
        Image img = new Image(R.drawable.white_pixel);

        // Create the walls and add them to the array
        for (int i = 0; i < 3; i++) {
            mWalls.add(WallFactory.createWall(i, img));
        }



        // Create player
        mPaddleOne = new Player(img, false);

        // Notify Player when touch events occur
        addTouchListener((Player)mPaddleOne);

        // Create player two
        mPaddleTwo = new Robot(img, true);

        // Create the main ball
        mBall = new Ball(img);

        // Notify Robot about each ball position
        mBall.addPositionListener((Robot)mPaddleTwo);

        reset();
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


    public void newRound() {
        // Set new random X-Speed for the ball
        float speedX = (float)Math.random() * 360 - 180;

        // Set new Y-speed depending on who's starting
        float speedY = (state.isPlayerOneStarting() ? -230f : 230f);

        // Calculate the ball placement
        float posX = Config.WINDOW_WIDTH / 2;
        float posY = Config.WINDOW_HEIGHT / 2;

        // Set it
        mBall.setSpeed(speedX, speedY);
        mBall.setPosition(posX, posY);


    }

    public void reset() {
        state = new GameState();

        mPaddleOne.resetPosition();
        mPaddleTwo.resetPosition();

		scoreTop.setLabel("0");
		scoreBtm.setLabel("0");

        newRound();
    }


    @Override
    public void update(float dt) {
        super.update(dt);

        mBall.update(dt);
        mPaddleOne.update(dt);
        mPaddleTwo.update(dt);

		if(mBall.collides(mPaddleOne)){
			mBall.handlePaddleCollision(mPaddleOne);
		}else if(mBall.collides(mPaddleTwo)){
			mBall.handlePaddleCollision(mPaddleTwo);
		}

		if(mBall.getY() > Config.WINDOW_HEIGHT){
            // Save and display score
            scoreTop.setLabel("" + state.incrementPlayerOne());

            // Restart
            state.setPlayerOneStarting(false);
			newRound();

		}else if(mBall.getY() + mBall.getScale().getY() < 0){
            // Save and display score
            scoreTop.setLabel("" + state.incrementPlayerTwo());

            // Restart
            state.setPlayerOneStarting(false);
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

		scoreTop.draw(canvas);
		scoreBtm.draw(canvas);
    }
}
