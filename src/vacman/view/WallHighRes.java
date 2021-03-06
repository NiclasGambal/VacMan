package vacman.view;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GRect;

/**
 * The graphic for the high resolution walls.
 */
public class WallHighRes extends GCompound {
	/** the background of the wall. */
	private GRect wall;
	/** the bricks inside the wall. */
	private GRect bigBrick;
	/** the bricks inside the wall. */
	private GRect bigBrickTwo;
	/** the bricks inside the wall. */
	private GRect bigBrickThree;
	/** the bricks inside the wall. */
	private GRect bigBrickFour;
	/** the bricks inside the wall. */
	private GRect bigBrickFive;
	/** the bricks inside the wall. */
	private GRect bigBrickSix;

	/**
	 * creates a 50x50 wall that looks like a cool wall
	 */
	public WallHighRes() {
		wall = new GRect(50, 50);
		wall.setFilled(true);
		wall.setFillColor(new Color(205, 0, 0));
		wall.setColor(Color.white);
		add(wall, 0, 0);

		bigBrick = new GRect(25, 12);
		bigBrick.setColor(Color.white);
		add(bigBrick, 0, 37.5);

		bigBrickTwo = new GRect(25, 12);
		bigBrickTwo.setColor(Color.white);
		add(bigBrickTwo, 25, 37.5);

		bigBrickThree = new GRect(25, 12);
		bigBrickThree.setColor(Color.white);
		add(bigBrickThree, 0, 12.5);

		bigBrickFour = new GRect(25, 12);
		bigBrickFour.setColor(Color.white);
		add(bigBrickFour, 25, 12.5);

		bigBrickFive = new GRect(25, 13);
		bigBrickFive.setColor(Color.white);
		add(bigBrickFive, 12.5, 0);

		bigBrickSix = new GRect(25, 13);
		bigBrickSix.setColor(Color.white);
		add(bigBrickSix, 12.5, 25);
	}

}