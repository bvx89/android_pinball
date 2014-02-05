package no.ntnu.assignment.two.model;

import java.util.ArrayList;

import no.ntnu.assignment.two.Config;
import no.ntnu.assignment.two.wall.CenterWall;
import no.ntnu.assignment.two.wall.LeftWall;
import no.ntnu.assignment.two.wall.RightWall;
import no.ntnu.assignment.two.wall.Wall;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by oknak_000 on 2/4/14.
 */
public class Ball extends Sprite implements PositionSubject {
    private ArrayList<PositionListener> listeners = new ArrayList<>();

	public Ball(Image image){
		super(image);
		setScale(Config.GRID_SIZE, Config.GRID_SIZE);
		setPosition(Config.WINDOW_WIDTH/2, Config.WINDOW_HEIGHT/2);
	}

	public void handleWallCollision(Wall wall){
		if(!(wall instanceof CenterWall)){
			if(getX() > wall.getX() && getSpeed().getX() < 0 ||
					getX() < wall.getX() && getSpeed().getX() > 0){
				setSpeed(-getSpeed().getX(), getSpeed().getY());
			}
		}
	}

	public void handlePaddleCollision(Paddle paddle){
		// Moves paddle center up or down according to what paddle the ball hits
		float addedCircleCenter = paddle.getScale().getX() / 4;
		if (paddle.getY() > Config.GRID_SIZE / 2)
			addedCircleCenter = -addedCircleCenter;

		// Handles collision like paddle is a ball, with center paddle.width / 4 lower than it's actual center to create more interesting ball paths
		Vector2 distance = new Vector2((getX() + getScale().getX() - (paddle.getX() + paddle.getScale().getX())),
				(getY() + getScale().getY()) - (paddle.getY() + paddle.getScale().getY()) + addedCircleCenter);

		// Sets new velocity vector, but keeps the same total speed.
		float length = distance.getLength();
		setSpeed(distance.getX() / length * getSpeed().getLength() * Config.BALL_SPEED_INCREASE,
				distance.getY() / length * getSpeed().getLength()  * Config.BALL_SPEED_INCREASE);
	}



    @Override
    public void update(float dt) {
        super.update(dt);
        notifyListeners();
    }

    @Override
    public void addPositionListener(PositionListener p) {
        listeners.add(p);
    }

    @Override
    public void removePositionListener(PositionListener p) {
        listeners.remove(p);
    }

    @Override
    public void notifyListeners() {
        for (PositionListener p : listeners) {
            p.notifyPosition(getX(), getY());
        }
    }
}
