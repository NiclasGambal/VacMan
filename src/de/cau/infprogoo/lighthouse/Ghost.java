package de.cau.infprogoo.lighthouse;

import java.awt.Color;
import java.awt.Point;

import acm.util.RandomGenerator;

/**
 * this class represents the model of the ghost with all their properties and algorithm how they move.
 *
 */
public class Ghost {
	/** a point that represents the current position of a goast. */
	private Point ghostPosition;
	
//	private Point ghostPrePos;
	/** the spawn position of a ghost */
	private Point ghostSpawnPosition;
	/** the direction of a ghosts movement. */
	private Direction ghostDirection;
	/** the map saved in this class. */
	private MapTiles[][] map;
	/** the color of a ghost. */
	private Color color;
	/** Random Generator instance. */
	private RandomGenerator rgen = RandomGenerator.getInstance();

	/**
	 * the constructor that creates a ghost.
	 * @param startPos the position where the goast starts and respawns after collusion.
	 * @param startDir  the direction of the goast at the beginning of the game. 
	 * @param map	the map where we play. a.k.a. the level as MapTiles Array.
	 * @param color	the specific color of a ghoast.
	 */
	public Ghost(Point startPos, Direction startDir, MapTiles[][] map, Color color) {
		// saving the inputs in instance variables of the goast and creatint a new ghoast
		ghostPosition = new Point(startPos);
		ghostSpawnPosition = new Point(startPos);
		ghostDirection = startDir;
		this.color = color;
		this.map = map;
	}
	/**
	 * the move method represents the KI of the ghosts and how they move.
	 */
	public void move() {
		// their speed or exactly their step which is added or subtracted from their coordinates
		int velocity = 1;
		// the probability of changing their direction by coincidence 
		double changeProbability = 0.2;
		// declaring the changable direction
		Direction change = Direction.DOWN;
		// picking a random int between 0 and 3
		int randy = rgen.nextInt(0, 3);
		// changing their direction with it
		if (randy == 0) {
			change = Direction.DOWN;
		} else if (randy == 1) {
			change = Direction.UP;
		} else if (randy == 2) {
			change = Direction.LEFT;
		} else if (randy == 3) {
			change = Direction.RIGHT;
		}
		// saving their position as local x and y value
		int ghostX = (int) ghostPosition.getX();
		int ghostY = (int) ghostPosition.getY();
		// if the ghost runs into a wall he changes his direction
		if (ghostDirection == Direction.LEFT) {
			if (map[ghostY][ghostX - 1] == MapTiles.WALL) {
				ghostDirection = change;
			}
			// if there is a field next to him with a coin or nothing he still has the probability of changing direction
			if (map[ghostY][ghostX - 1] == MapTiles.COIN || map[ghostY][ghostX - 1] == MapTiles.VOID) {
				if (rgen.nextBoolean(changeProbability)) {
					ghostDirection = change;
				}
				// moving to the field with void or coin if direction is not changed
				ghostPosition.setLocation(ghostPosition.getX() - velocity, ghostPosition.getY());
			}
			// same for the right direction
		} else if (ghostDirection == Direction.RIGHT) {
			if (map[ghostY][ghostX + 1] == MapTiles.WALL) {
				ghostDirection = change;
			}
			if (map[ghostY][ghostX + 1] == MapTiles.COIN || map[ghostY][ghostX + 1] == MapTiles.VOID) {
				if (rgen.nextBoolean(changeProbability)) {
					ghostDirection = change;
				}
				// adding velocity at x coordinate to move right
				ghostPosition.setLocation(ghostPosition.getX() + velocity, ghostPosition.getY());
			}
			// same way for up-direction
		} else if (ghostDirection == Direction.UP) {
			if (map[ghostY - 1][ghostX] == MapTiles.WALL) {
				ghostDirection = change;
			}
			if (map[ghostY - 1][ghostX] == MapTiles.VOID || map[ghostY - 1][ghostX] == MapTiles.COIN) {
				if (rgen.nextBoolean(changeProbability)) {
					ghostDirection = change;
				}
				ghostPosition.setLocation(ghostPosition.getX(), ghostPosition.getY() - velocity);
			}
			// last but not least the down- direction
		} else if (ghostDirection == Direction.DOWN) {
			if (map[ghostY + 1][ghostX] == MapTiles.WALL) {
				ghostDirection = change;
			}
			if (map[ghostY + 1][ghostX] == MapTiles.COIN || map[ghostY + 1][ghostX] == MapTiles.VOID) {
				if (rgen.nextBoolean(changeProbability)) {
					ghostDirection = change;
				}
				ghostPosition.setLocation(ghostPosition.getX(), ghostPosition.getY() + velocity);
			}
		}
	}
	/**
	 * gets the current position of a ghost.
	 * @return the current position.
	 */
	public Point getPos() {
		return ghostPosition;
	}
	/**
	 * setter for the position of a ghost.
	 * @param pos the position where the ghost should be.
	 */
	public void setPos(Point pos) {
		ghostPosition.setLocation(pos);
	}
	/**
	 * gets the color of a ghost.
	 * @return	the color a ghost has.
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * gets the spawn point of a ghost. needed for reset into its home.
	 * @return the spawn position.
	 */
	public Point getGhostSpawnPos() {
		return ghostSpawnPosition;
	}
}
