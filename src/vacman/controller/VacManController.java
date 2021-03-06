package vacman.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vacman.model.Direction;
import vacman.model.VacManModel;

/**
 * This class is about the controlls and user model interactions from the VacMan
 * game. It extends MouseAdapter and implements the KeyListener interface, to
 * get the methods of both mouse and key events.
 */
public class VacManController extends MouseAdapter implements KeyListener {
	/** Instance that keeps the corresponding model. */
	private VacManModel model;

	/**
	 * The Constructor of the controller.
	 * 
	 * @param model The model the controller should have access to.
	 */
	public VacManController(VacManModel model) {
		this.model = model;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Changes the direction of VacMan with the arrow key or w,a,s,d keys.
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			model.setVacManDir(Direction.UP);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			model.setVacManDir(Direction.DOWN);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			model.setVacManDir(Direction.LEFT);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			model.setVacManDir(Direction.RIGHT);
		}
		// Changes the map and level from winscreen to level 1, when enter got pressed.
		if (model.getLevel() == 0) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				model.setLevel(1);
				model.setCurrentMap(model.getLevel1Map());
			}
		}
		// Changes the map and level from gameover to winscreen, when enter got pressed.
		if (model.getLevel() == -1) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				model.setLevel(0);
				model.setCurrentMap(model.getWinMap());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Should do the same like the enter button, but does nothing.
		if (model.getLevel() == 0) {
			model.setLevel(1);
			model.setCurrentMap(model.getLevel1Map());
		} else if (model.getLevel() == -1) {
			model.setLevel(0);
			model.setCurrentMap(model.getWinMap());
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
