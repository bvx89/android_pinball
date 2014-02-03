package no.ntnu.assignment.two;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import java.util.ArrayList;

import no.ntnu.assignment.two.wall.Wall;
import no.ntnu.assignment.two.wall.WallFactory;
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
public class PongMachine extends State implements TouchListener {

    private static PongMachine instance;

    private ArrayList<Sprite> mSpriteObjects = new ArrayList<>();

    private PongMachine() {
        super.addTouchListener(this);

        // Create the walls and add them to the array
        for (int i = 0; i < 3; i++) {
            mSpriteObjects.add(WallFactory.createWall(
                    i,
                    new Image(R.drawable.white_pixel),
                    MainActivity.WINDOW_WIDTH,
                    MainActivity.WINDOW_HEIGHT));
        }

    }


    public static PongMachine getInstance() {
        if (instance == null) {
            instance = new PongMachine();
        }

        return instance;
    }


    @Override
    public void update(float dt) {
        //TODO - call update method to each object
        for (Sprite s : mSpriteObjects) {
            s.update(dt);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.DKGRAY);

        for (Sprite s : mSpriteObjects) {
            s.draw(canvas);
        }

    }


    /*
     * Handling all touch-events to replicate
     * the movement inside of pong
     */
    @Override
    public boolean onTouchDown(MotionEvent event) {
        //TODO - Propagate the event to each object

        return true;
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        //TODO - Propagate the event to each object

        return true;
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {
        //TODO - Propagate the event to each object

        return true;
    }

}
