package vacman.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import vacman.model.Direction;
import vacman.model.Ghost;
import vacman.model.MapTiles;
import vacman.model.Screen;
import vacman.model.VacManModel;

/**
 * This class is about the controlls and user model interactions from the VacMan
 * game. It extends MouseAdapter and implements the KeyListener interface, to
 * get the methods of both mouse and key events.
 */
public class VacManController extends MouseAdapter implements KeyListener {
	/** Instance that keeps the corresponding model. */
	private VacManModel model;
	private RandomGenerator rgen = new RandomGenerator().getInstance();

	/**
	 * The Constructor of the controller.
	 * 
	 * @param model The model the controller should have access to.
	 */
	public VacManController(VacManModel model) {
		this.model = model;

	}

	<T> T[][] deepCopy(T[][] matrix) {
		return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (model.getCurrentScreen() == Screen.CURRENTLEVEL) {

			// Changes the direction of VacMan with the arrow key or w,a,s,d keys.
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				model.setVacManDir(Direction.UP);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				model.setVacManDir(Direction.DOWN);
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				model.setVacManDir(Direction.LEFT);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				model.setVacManDir(Direction.RIGHT);
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				model.setCurrentScreen(Screen.SELECTION);
			}
		}
		// Changes the screen from start to level selection.
		else if (model.getCurrentScreen() == Screen.START) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				model.setCurrentScreen(Screen.SELECTION);
			}
		}
		// Changes the map and level from gameover to winscreen, when enter got pressed.
		else if (model.getCurrentScreen() == Screen.SELECTION) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				model.setCurrentScreen(Screen.START);
			}
		} else if (model.getCurrentScreen() == Screen.SETTINGS) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				model.setCurrentScreen(Screen.SELECTION);
			}
		} else if (model.getCurrentScreen() == Screen.WIN) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				model.setCurrentScreen(Screen.SELECTION);
			}
		} else if (model.getCurrentScreen() == Screen.GAMEOVER) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				model.setCurrentScreen(Screen.START);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// Should do the same like the enter button, but does nothing.
		if (model.getCurrentScreen() == Screen.SELECTION) {
			if (new GRect(2 * model.getCurrentView().getRes(), 4 * model.getCurrentView().getRes(),
					7 * model.getCurrentView().getRes(), 2 * model.getCurrentView().getRes()).contains(e.getX(),
							e.getY())) {

				model.setActiveLevel(model.getMaps().get(0));
				model.reset();
				model.setVacManPosition(model.getActiveLevel().getVacmanStartPos().x,
						model.getActiveLevel().getVacmanStartPos().y);
				model.setVacmanSpawnPosition(new Point(model.getActiveLevel().getVacmanStartPos()));
				ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
				for (Point point : model.getActiveLevel().getGhostStartPos()) {
					ghosts.add(new Ghost(point, Direction.DOWN, model.getActiveLevel().getMap(), rgen.nextColor()));
				}
				model.setGhosts(ghosts);
				model.setCurrentMap(deepCopy(model.getActiveLevel().getMap()));
				int coins = 0;
				for (int r = 0; r < model.getCurrentMap().length; r++) {
					for (int c = 0; c < model.getCurrentMap()[1].length; c++) {
						if (model.getCurrentMap()[r][c] == MapTiles.COIN) {
							coins++;
						}
					}
				}
				model.setPoints(coins);
				model.setCurrentScreen(Screen.CURRENTLEVEL);

			} else if (new GRect(2 * model.getCurrentView().getRes(), 7 * model.getCurrentView().getRes(),
					7 * model.getCurrentView().getRes(), 2 * model.getCurrentView().getRes()).contains(e.getX(),
							e.getY())) {
				model.setActiveLevel(model.getMaps().get(1));
				model.reset();
				model.setVacManPosition(model.getActiveLevel().getVacmanStartPos().x,
						model.getActiveLevel().getVacmanStartPos().y);
				model.setVacmanSpawnPosition(new Point(model.getActiveLevel().getVacmanStartPos()));
				ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
				for (Point point : model.getActiveLevel().getGhostStartPos()) {
					ghosts.add(new Ghost(point, Direction.DOWN, model.getActiveLevel().getMap(), rgen.nextColor()));
				}
				model.setGhosts(ghosts);
				model.setCurrentMap(deepCopy(model.getActiveLevel().getMap()));
				int coins = 0;
				for (int r = 0; r < model.getCurrentMap().length; r++) {
					for (int c = 0; c < model.getCurrentMap()[1].length; c++) {
						if (model.getCurrentMap()[r][c] == MapTiles.COIN) {
							coins++;
						}
					}
				}
				model.setPoints(coins);
				model.setCurrentScreen(Screen.CURRENTLEVEL);

			} else if (new GRect(2 * model.getCurrentView().getRes(), 10 * model.getCurrentView().getRes(),
					7 * model.getCurrentView().getRes(), 2 * model.getCurrentView().getRes()).contains(e.getX(),
							e.getY())) {
				model.setActiveLevel(model.getMaps().get(2));
				model.reset();
				model.setVacManPosition(model.getActiveLevel().getVacmanStartPos().x,
						model.getActiveLevel().getVacmanStartPos().y);
				model.setVacmanSpawnPosition(new Point(model.getActiveLevel().getVacmanStartPos()));
				ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
				for (Point point : model.getActiveLevel().getGhostStartPos()) {
					ghosts.add(new Ghost(point, Direction.DOWN, model.getActiveLevel().getMap(), rgen.nextColor()));
				}
				model.setGhosts(ghosts);
				model.setCurrentMap(deepCopy(model.getActiveLevel().getMap()));
				int coins = 0;
				for (int r = 0; r < model.getCurrentMap().length; r++) {
					for (int c = 0; c < model.getCurrentMap()[1].length; c++) {
						if (model.getCurrentMap()[r][c] == MapTiles.COIN) {
							coins++;
						}
					}
				}
				model.setPoints(coins);
				model.setCurrentScreen(Screen.CURRENTLEVEL);

			} else if (new GRect(24 * model.getCurrentView().getRes(), 10 * model.getCurrentView().getRes(),
					3 * model.getCurrentView().getRes(), 3 * model.getCurrentView().getRes()).contains(e.getX(),
							e.getY())) {
				model.setCurrentScreen(Screen.SETTINGS);
			}
		} else if (model.getCurrentScreen() == Screen.SETTINGS) {
			// Highresolution.
			if (new GRect(2 * model.getCurrentView().getRes(), 4 * model.getCurrentView().getRes(),
					7 * model.getCurrentView().getRes(), 2 * model.getCurrentView().getRes()).contains(e.getX(),
							e.getY())) {

				model.setCurrentView(model.getViews().get(1));

			}
			// Coronasolution.
			else if (new GRect(2 * model.getCurrentView().getRes(), 7 * model.getCurrentView().getRes(),
					7 * model.getCurrentView().getRes(), 2 * model.getCurrentView().getRes()).contains(e.getX(),
							e.getY())) {

				model.setCurrentView(model.getViews().get(2));

			}
			// Full HD View.
			else if (new GRect(2 * model.getCurrentView().getRes(), 10 * model.getCurrentView().getRes(),
					7 * model.getCurrentView().getRes(), 2 * model.getCurrentView().getRes()).contains(e.getX(),
							e.getY())) {
				model.setCurrentView(model.getViews().get(3));

			}
			// Lighthouse check
			else if (new GRect(11 * model.getCurrentView().getRes(), 7 * model.getCurrentView().getRes(),
					7 * model.getCurrentView().getRes(), 2 * model.getCurrentView().getRes()).contains(e.getX(),
							e.getY())) {

				model.setLightHouseOn(!model.getLightHouseOn());

			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

}
