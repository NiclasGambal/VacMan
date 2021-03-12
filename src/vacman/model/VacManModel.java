package vacman.model;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import vacman.view.CoronaResView;
import vacman.view.HDResView;
import vacman.view.HighResView;
import vacman.view.LightHouseView;
import vacman.view.VacManView;

/**
 * This class keeps track with data in the array and the model of the game but
 * is not responsible for the actual display. Whenever the controller got new
 * input, the model notifies all registered views.
 */
public class VacManModel {

	/** The current view. */
	private VacManView<VacManModel> currentView;
	/** The current map. */
	private MapTiles[][] map = new MapTiles[14][28];
	/** The position of VacMan. */
	private Point vacmanPosition;
	/** Position of VacMan one time step before. */
	private Point vacmanPrePos = new Point();
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
	private ArrayList<VacManView<VacManModel>> views = new ArrayList<VacManView<VacManModel>>();
	/** Boolean which is true if the LHV is on. */
	private boolean lightHouseOn = false;

	/** Creates the model of the game with the current level. */
	public VacManModel() {
		try {
			LevelFileLoader loader = new LevelFileLoader();
			maps = loader.getMaps();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LightHouseView lightV = new LightHouseView();
		CoronaResView coronaV = new CoronaResView();
		HighResView highV = new HighResView();
		HDResView hdV = new HDResView();
		views.add(lightV);
		views.add(highV);
		views.add(coronaV);
		views.add(hdV);
		currentView = highV;

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
	public static MapTiles[][] intToMapTiles(int[][] map) {
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
				if (map[r][c] == 6) {
					tileMap[r][c] = MapTiles.BACKGROUND;
				}
				if (map[r][c] == 7) {
					tileMap[r][c] = MapTiles.HOCHHAUS;
				}
				if (map[r][c] == 8) {
					tileMap[r][c] = MapTiles.FENSTER;
				}
				if (map[r][c] == 9) {
					tileMap[r][c] = MapTiles.SCHRIFT;
				}
				if (map[r][c] == 10) {
					tileMap[r][c] = MapTiles.ENTERSYMBOL;
				}
			}
		}
		return tileMap;
	}

	public void reset() {
		hearts = 3;
		vacmanDir = null;
	}

	public void setPoints(int points) {
		numberOfPoints = points;
	}

	public void setGhosts(ArrayList<Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public void setActiveLevel(Level level) {
		this.activeLevel = level;
	}

	public Level getActiveLevel() {
		return activeLevel;
	}

	public void setVacmanSpawnPosition(Point vacmanSpawnPosition) {
		this.vacmanSpawnPosition = vacmanSpawnPosition;
	}

	public ArrayList<Level> getMaps() {
		return maps;
	}

	/** Setter for the lighthouse. */
	public void setLightHouseOn(boolean lightHouseOn) {
		this.lightHouseOn = lightHouseOn;
	}

	/** Getter for the light house on. */
	public boolean getLightHouseOn() {
		return lightHouseOn;
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

	/** Setter for the current screen. */
	public void setCurrentScreen(Screen currentScreen) {
		this.currentScreen = currentScreen;
	}

	/** Setter for the current level. */
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	/** Getter for the current screen. */
	public Screen getCurrentScreen() {
		return currentScreen;
	}

	/** Getter for the Ghost arraylist. */
	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	/** renders the current view of the model. */
	public void notifyViews() {
		currentView.render(this);
		if (lightHouseOn) {
			views.get(0).render(this);
		}
	}

	/** Getter for the current view. */
	public VacManView<VacManModel> getCurrentView() {
		return currentView;
	}

	/** Setter for the current view list. */
	public void setCurrentView(VacManView<VacManModel> currentView) {
		this.currentView = currentView;
	}

	public ArrayList<VacManView<VacManModel>> getViews() {
		return views;
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

	/** The method that is called when the game is gameover. */
	public void gameover() {
		// Switches to the gameover map and sets the corresponding level.

		currentScreen = Screen.GAMEOVER;

	}

	/** The method that is called when the game is won. */
	public void win() {
		// Switches to the win map and sets the corresponding level.

		currentScreen = Screen.WIN;

	}

	/**
	 * A method that updates everything in the model and also notifies the
	 * correlated views.
	 */
	public void update() {

		if (currentScreen == Screen.CURRENTLEVEL) {

			vacmanPrePos.setLocation(vacmanPosition);
			for (Ghost ghost : ghosts) {
				ghost.setGhostPrePos(ghost.getPos());
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
			// Goes through all ghosts and checks if their position collides with VacMans.
			for (Ghost ghost : ghosts) {

				if (vacmanPosition.equals(ghost.getPos())
						|| (vacmanPosition.equals(ghost.getGhostPrePos()) && vacmanPrePos.equals(ghost.getPos()))) {
					// If so, a heart disappears and the heart counter gets decremented.
					hearts--;
					if (hearts == 2) {
						map[0][2] = MapTiles.VOID;
						// Death animation.

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
		}
		// And the view is updated as well.
		notifyViews();
	}

}
