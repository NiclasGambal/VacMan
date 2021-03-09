package vacman.model;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import vacman.view.VacManView;

/**
 * This class keeps track with data in the array and the model of the game but
 * is not responsible for the actual display. Whenever the controller got new
 * input, the model notifies all registered views.S
 */
public class VacManModel {

	/** The current view. */
	private VacManView<VacManModel> view;
	/** The gameover screen as tile map. */
	private MapTiles[][] gameoverMap = new MapTiles[14][28];
	/** The win screen as tile map. */
	private MapTiles[][] winMap = new MapTiles[14][28];
	/** The current map. */
	private MapTiles[][] map = new MapTiles[14][28];
	/** The tile map for level one. */
	private MapTiles[][] mapLevel1 = new MapTiles[14][28];
	/** The position of VacMan. */
	private Point vacmanPosition;
	/** Position of VacMan one time step before. */
	private Point vacmanPrePos;
	/** Spawning position of VacMan. */
	private Point vacmanSpawnPosition;
	/** Array of ghosts from the current map. */
	private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	/** Max. number of the collectable points. */
	private int numberOfPoints;
	/** Counter for the lives. */
	private int hearts;
	/** The number of the current Map. */
	private int currentLevel = -1;
	/** Direction of the VacMan movement. */
	private Direction vacmanDir;
	/** The currently displayed screen. */
	private Screen currentScreen = Screen.START;

	/** ArrayList of the levels. */
	private ArrayList<Level> maps;
	/** Currently active level. */
	private Level activeLevel;
	/** List of the views that are available at the moment. */
	private ArrayList<VacManView<VacManModel>> views;

	/** Creates the model of the game with the current level. */
	public VacManModel(int level, VacManView<VacManModel> currentView) {
		try {
			LevelFileLoader loader = new LevelFileLoader();
			maps = loader.getMaps();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (currentScreen == Screen.CURRENTLEVEL) {
			activeLevel = maps.get(currentLevel);
			map = activeLevel.getMap();
			vacmanPosition.setLocation(activeLevel.getVacmanStartPos());
			for (Point ghostStartPos : activeLevel.getGhostStartPos()) {
				ghosts.add(new Ghost(ghostStartPos, Direction.DOWN, activeLevel.getMap(), Color.CYAN));
			}

		}

		// Initializes everything.
		hearts = 3;
		view = currentView;
		numberOfPoints = 128;
		vacmanSpawnPosition = new Point(14, 4);
		vacmanPosition = new Point(14, 4);

		// Represents the first VacMan map in an array. 3: Hearts, 2: Points, 1: Walls
		// and 0: empty.
		int[][] visualMap = {
				// row 0
				{ 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 },
				// row 1
				{ 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0 },
				// row 2
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				// row 3
				{ 1, 2, 2, 2, 2, 2, 2, 0, 0, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 0, 0, 2, 2, 2, 2, 2, 2, 1 },
				// row 4
				{ 1, 2, 1, 1, 1, 1, 2, 0, 2, 2, 1, 0, 2, 2, 0, 2, 0, 1, 2, 2, 0, 2, 1, 1, 1, 1, 2, 1 },
				// row 5
				{ 1, 2, 1, 2, 2, 2, 2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 1 },
				// row 6
				{ 1, 2, 1, 2, 1, 1, 1, 1, 2, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 2, 1, 1, 1, 1, 2, 1, 2, 1 },
				// row 7
				{ 1, 2, 1, 2, 1, 2, 2, 2, 2, 0, 0, 1, 2, 2, 2, 2, 1, 0, 0, 2, 2, 2, 2, 1, 2, 1, 2, 1 },
				// row 8
				{ 1, 2, 1, 2, 2, 1, 1, 1, 2, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 2, 1, 1, 1, 2, 2, 1, 2, 1 },
				// row 9
				{ 1, 2, 1, 2, 2, 1, 1, 1, 2, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 2, 1, 1, 1, 2, 2, 1, 2, 1 },
				// row 10
				{ 1, 2, 2, 2, 2, 1, 1, 1, 2, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 2, 1, 1, 1, 2, 2, 2, 2, 1 },
				// row 11
				{ 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1 },
				// row 12
				{ 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0 },
				// row 13
				{ 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 } };
		// The map is converted into a MapTiles map.
		map = intToMapTiles(visualMap);
		mapLevel1 = map;
		int[][] gameoverscreen = {
				// row 0
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 1
				{ 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 2
				{ 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 3
				{ 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 4
				{ 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 5
				{ 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 6
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 7
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 8
				{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
				// row 9
				{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
				// row 10
				{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
				// row 11
				{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
				// row 12
				{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
				// row 13
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		// This map also will be converted.
		gameoverMap = intToMapTiles(gameoverscreen);
		int[][] winscreen = { // row 0
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 1
				{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
				// row 2
				{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
				// row 3
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
				// row 4
				{ 0, 3, 3, 0, 3, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
				// row 5
				{ 3, 3, 3, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0 },
				// row 6
				{ 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 7
				{ 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 8
				{ 0, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
				// row 9
				{ 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
				// row 10
				{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
				// row 11
				{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				// row 12
				{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
				// row 13
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		winMap = intToMapTiles(winscreen);
		// Adds two Ghost objects to the Ghost arraylist.
		ghosts.add(new Ghost(new Point(13, 9), Direction.DOWN, map, Color.CYAN));
		ghosts.add(new Ghost(new Point(13, 10), Direction.DOWN, map, Color.MAGENTA));
	}

	/**
	 * A method to convert an array with integer values from 0 to 3 into a MapTiles
	 * array with the correlated values.
	 * 
	 * @param map Array to be converted.
	 * @return MapTiles array as a converted form of the former Map with the related
	 *         MapTiles values or an exception, if the values do not match any
	 *         MapTiles values.
	 */
	public MapTiles[][] intToMapTiles(int[][] map) {
		// Initializes MapTiles map.
		MapTiles[][] tileMap = new MapTiles[14][28];
		// Converts/copies the int map into the MapTiles map.
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] == 1) {
					tileMap[r][c] = MapTiles.WALL;
				}
				if (map[r][c] == 2) {
					tileMap[r][c] = MapTiles.COIN;
				}
				if (map[r][c] == 3) {
					tileMap[r][c] = MapTiles.HEART;
				}
				if (map[r][c] == 0) {
					tileMap[r][c] = MapTiles.VOID;
				}
				if (map[r][c] > 3) {
					throw new IllegalArgumentException("Not included in MapTiles.");
				}
			}
		}
		return tileMap;
	}

	/** Setter for the Direction of VacMan. */
	public void setVacManDir(Direction dir) {
		vacmanDir = dir;
	}

	/** Getter for the Direction of VacMan. */
	public Direction getVacManDir() {
		return vacmanDir;
	}

	/** Setter for the VacMan Position. */
	public void setVacManPosition(int x, int y) {
		vacmanPosition = new Point(x, y);
	}

	/** Getter for the VacMan Position. */
	public Point getVacManPos() {
		return vacmanPosition;
	}

	/** Getter for the Ghost arraylist. */
	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	/** renders the current view of the model. */
	public void notifyView() {
		view.render(this);
	}

	/** Getter for the current view. */
	public VacManView getVacManView() {
		return view;
	}

	/** Returns a copy of the current map array. */
	public MapTiles[][] getCurrentMap() {
		return map;
	}

	/** Setter of the current map. */
	public void setCurrentMap(MapTiles[][] map) {
		this.map = map;
	}

	/** Getter of the current level. */
	public int getLevel() {
		return currentLevel;
	}

	/** Setter of the current level. */
	public void setLevel(int level) {
		currentLevel = level;
	}

	/** Getter of the current win screen map. */
	public MapTiles[][] getWinMap() {
		return winMap;
	}

	/** Getter for the level one map. */
	public MapTiles[][] getLevel1Map() {
		return mapLevel1;
	}

	/** The method that is called when the game is gameover. */
	public void gameover() {
		// Switches to the gameover map and sets the corresponding level.
		map = gameoverMap;
		currentLevel = -1;
		// Also resets numberOfPoints and hearts.
		numberOfPoints = 128;
		hearts = 3;

	}

	/** The method that is called when the game is won. */
	public void win() {
		// Switches to the win map and sets the corresponding level.
		map = winMap;
		currentLevel = 0;
		// Also resets numberOfPoints and hearts.
		numberOfPoints = 128;
		hearts = 3;

	}

	/**
	 * A method that updates everything in the model and also notifies the
	 * correlated views.
	 */
	public void update() {
		// Goes through all ghosts and checks if their position collides with VacMans.
		for (Ghost ghost : ghosts) {

			if (vacmanPosition.equals(ghost.getPos())) {
				// If so, a heart disappears and the heart counter gets decremented.
				hearts--;
				if (hearts == 2) {
					map[0][2] = MapTiles.VOID;
					// Deathanimation.

				} else if (hearts == 1) {
					map[0][1] = MapTiles.VOID;
					// Explosionmethod.

				} else if (hearts == 0) {
					map[0][0] = MapTiles.VOID;
					// If the last heart disappears the game is over and so the gameover method is
					// called. Also VacMan position and direction is reseted.
					vacmanPosition.setLocation(vacmanSpawnPosition);
					vacmanDir = null;
					gameover();

				}
				// After VacMan hits a ghost, the VacMan and ghosts are reseted to their
				// spawnpoints.
				vacmanPosition.setLocation(vacmanSpawnPosition);
				for (Ghost ghost1 : ghosts) {
					ghost1.setPos(ghost1.getGhostSpawnPos());
				}

				vacmanDir = null;
			}
		}
		// If the numberOfPoints counter reaches 0 all coins are collected and the game
		// is won.
		if (numberOfPoints == 0) {
			// Resets all positions to their spawnpoints.
			for (Ghost ghost1 : ghosts) {
				ghost1.setPos(ghost1.getGhostSpawnPos());
			}
			vacmanPosition.setLocation(vacmanSpawnPosition);
			vacmanDir = null;
			// And calls the win method.
			win();
		}
		// Here the movement part starts.
		int velocity = 1;
		int vacX = (int) vacmanPosition.getX();
		int vacY = (int) vacmanPosition.getY();

		// Checks in which direction VacMan runs right now and checks the corresponding
		// field he is moving into.
		if (vacmanDir == Direction.LEFT) {
			// Checks the MapTiles of the next cell of the map array.
			if (map[vacY][vacX - 1] == MapTiles.COIN) {

				map[vacY][vacX - 1] = MapTiles.VOID;
				// If a coin got collected the counter is decremented.
				numberOfPoints--;
				// VacMan moves to the next cell, if it is no wall.
				vacmanPosition.setLocation(vacmanPosition.getX() - velocity, vacmanPosition.getY());
			} else if (map[vacY][vacX - 1] == MapTiles.VOID) {

				vacmanPosition.setLocation(vacmanPosition.getX() - velocity, vacmanPosition.getY());
			}
			// Same for the other directions.
		} else if (vacmanDir == Direction.RIGHT) {

			if (map[vacY][vacX + 1] == MapTiles.COIN) {

				map[vacY][vacX + 1] = MapTiles.VOID;
				numberOfPoints--;
				vacmanPosition.setLocation(vacmanPosition.getX() + velocity, vacmanPosition.getY());
			} else if (map[vacY][vacX + 1] == MapTiles.VOID) {

				vacmanPosition.setLocation(vacmanPosition.getX() + velocity, vacmanPosition.getY());
			}

		} else if (vacmanDir == Direction.UP) {

			if (map[vacY - 1][vacX] == MapTiles.COIN) {

				map[vacY - 1][vacX] = MapTiles.VOID;
				numberOfPoints--;
				vacmanPosition.setLocation(vacmanPosition.getX(), vacmanPosition.getY() - velocity);
			} else if (map[vacY - 1][vacX] == MapTiles.VOID) {

				vacmanPosition.setLocation(vacmanPosition.getX(), vacmanPosition.getY() - velocity);
			}

		} else if (vacmanDir == Direction.DOWN) {

			if (map[vacY + 1][vacX] == MapTiles.COIN) {

				map[vacY + 1][vacX] = MapTiles.VOID;
				numberOfPoints--;
				vacmanPosition.setLocation(vacmanPosition.getX(), vacmanPosition.getY() + velocity);
			} else if (map[vacY + 1][vacX] == MapTiles.VOID) {

				vacmanPosition.setLocation(vacmanPosition.getX(), vacmanPosition.getY() + velocity);
			}

		}
		// After VacMan moved, every ghost is moved.
		for (Ghost ghost : ghosts) {
			ghost.move();
		}
		// And the view is updated as well.
		notifyView();
	}

}
