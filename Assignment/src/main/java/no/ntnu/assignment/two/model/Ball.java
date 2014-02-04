package no.ntnu.assignment.two.model;

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
public class Ball extends Sprite {
	private int mHeight;
	private final float speedIncrease = 1.05f;

	public Ball(Image image, int size, int x, int y, int mHeight){
		super(image);
		setScale(size,size);
		setPosition(x,y);
		this.mHeight = mHeight;
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
		if (paddle.getY() > mHeight / 2)
			addedCircleCenter = -addedCircleCenter;

		// Handles collision like paddle is a ball, with center paddle.width / 4 lower than it's actual center to create more interesting ball paths
		Vector2 distance = new Vector2((getX() + getScale().getX() - (paddle.getX() + paddle.getScale().getX())),
				(getY() + getScale().getY()) - (paddle.getY() + paddle.getScale().getY()) + addedCircleCenter);

		// Sets new velocity vector, but keeps the same total speed.
		float length = distance.getLength();
		setSpeed(distance.getX() / length * getSpeed().getLength() * speedIncrease,
				distance.getY() / length * getSpeed().getLength()  * speedIncrease);
	}

}
